package com.apprentice.app.api.controller;

import com.apprentice.app.service.domain.post.PostDetailResponseDto;
import com.apprentice.app.service.domain.post.PostRequestDto;
import com.apprentice.app.service.domain.post.PostResponseDto;
import com.apprentice.app.service.domain.token.TokenProvider;
import com.apprentice.app.service.interfaces.PostService;
import com.apprentice.app.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final TokenProvider tokenProvider;

    private final PostService postService;

    // GET
    @GetMapping("/post/{id}")
    public ResponseEntity<Object> post(@PathVariable String id) {
        PostDetailResponseDto result;

        try {
            result = postService.searchPostDetail(id);
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
            return ResponseEntity.ok().body("ok");
        }
    }
}
