package com.apprentice.app.api.controller;

import com.apprentice.app.service.domain.post.PostDetailResponseDto;
import com.apprentice.app.service.domain.post.PostRequestDto;
import com.apprentice.app.service.domain.post.PostResponseDto;
import com.apprentice.app.service.domain.postLike.PostLikeId;
import com.apprentice.app.service.domain.postLike.PostLikeRequestDto;
import com.apprentice.app.service.domain.token.TokenProvider;
import com.apprentice.app.service.interfaces.PostService;
import com.apprentice.app.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final TokenProvider tokenProvider;

    private final PostService postService;

    // GET
    @GetMapping("/post")
    public ResponseEntity<Object> post_all(@RequestParam("type") String type, @RequestParam("period") String period,
                                           @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        LocalDateTime from;
        LocalDateTime to = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        if (period.equals("today")) { // 하루
            from = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
        } else if (period.equals("week")) { // 주간
            from = LocalDateTime.of(LocalDate.now().minusWeeks(1), LocalTime.of(0, 0, 0));
        } else if (period.equals("month")) { //월간
            from = LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.of(0, 0, 0));
        } else { //연간
            from = LocalDateTime.of(LocalDate.now().minusYears(1), LocalTime.of(0, 0, 0));
        }

        List<PostResponseDto> result = null;
        if (type.equals("trend")) {
            PageRequest pageReq = PageRequest.of(offset - 1, limit, Sort.by("hits").descending()); // offset은 0부터 시작
            result = postService.searchPostByUpdatedPaging(from, to, pageReq);
        } else if (type.equals("recent")) {
            PageRequest pageReq = PageRequest.of(offset - 1, limit, Sort.by("updated").descending());
            result = postService.searchPostByUpdatedPaging(from, to, pageReq);
        } else if (type.equals("follow")) {
            //TODO : 계정의 follow 계정 정보 기능 구성 후 작업
        }

        if (result == null || result.isEmpty()) return ResponseEntity.badRequest().body("nodata");
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Object> post_select(@PathVariable String id, HttpServletRequest request) {
        PostDetailResponseDto result;

        String user = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();

        try {
            result = postService.searchPostDetail(id, user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("포스트가 존재하지 않습니다.");
        }

        if (result == null) return ResponseEntity.badRequest().body("존재하지 않는 포스트입니다.");
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/post/saves")
    public ResponseEntity<Object> post_saves() {
        return ResponseEntity.ok().body(postService.searchPostSaves(3));
    }

    // POST
    @PostMapping("/post")
    public ResponseEntity<Object> post(@RequestBody PostRequestDto reqDto, HttpServletRequest request) {
        if (!reqDto.isValid()) {
            return ResponseEntity.badRequest().body("제목과 내용은 필수 입력사항입니다.");
        }

        String writer = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();
        reqDto.setWriter(writer);

        String id = postService.writePost(reqDto);

        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        } else {
            return ResponseEntity.ok().body(id);
        }
    }

    @RequestMapping(value = "/post/{id}/like", method = RequestMethod.POST)
    public ResponseEntity<Object> postLike(@PathVariable String id, HttpServletRequest request) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("포스트 아이디를 입력해주세요.");
        }

        String user = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();

        String res_id = postService.writePostLike(PostLikeRequestDto.builder().post_id(id).member_id(user).build());

        if (res_id == null || res_id.isEmpty()) {
            return ResponseEntity.badRequest().body("좋아요 등록에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        } else {
            return ResponseEntity.ok().body("ok");
        }
    }

    // PATCH
    @RequestMapping(value = "/post/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> postPatch(@RequestBody PostRequestDto reqDto, @PathVariable String id, HttpServletRequest request) {
        if (!reqDto.isValid()) {
            return ResponseEntity.badRequest().body("제목과 내용은 필수 입력사항입니다.");
        }

        String reqWriter = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();
        PostResponseDto target = postService.searchPost(id);

        if (target == null) {
            return ResponseEntity.badRequest().body("존재하지 않는 게시글입니다.");
        } else if (!target.getWriter().equals(reqWriter)) {
            return ResponseEntity.badRequest().body("수정 권한이 없는 게시글입니다.");
        }

        reqDto.setWriter(reqWriter);

        String editedId = postService.editPost(id, reqDto);

        if (editedId == null || editedId.isEmpty()) {
            return ResponseEntity.badRequest().body("수정에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        } else {
            return ResponseEntity.ok().body(editedId);
        }
    }

    //DELETE
    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> postDelete(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("포스트 아이디를 입력해주세요.");
        }

        //TODO : 삭제 구현 안 되있음

        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(value = "/post/{id}/like", method = RequestMethod.DELETE)
    public ResponseEntity<Object> postUnlike(@PathVariable String id, HttpServletRequest request) {
        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("포스트 아이디를 입력해주세요.");
        }

        String user = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();

        int res_cnt = postService.deletePostLike(new PostLikeId(id, user));

        if (res_cnt == 0) {
            return ResponseEntity.badRequest().body("좋아요 취소에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        } else {
            return ResponseEntity.ok().body("ok");
        }
    }
}
