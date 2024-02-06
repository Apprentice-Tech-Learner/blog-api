package com.apprentice.app.api.controller;

import com.apprentice.app.service.domain.postSeries.PostSeriesRequestDto;
import com.apprentice.app.service.domain.postSeries.PostSeriesResponseDto;
import com.apprentice.app.service.domain.token.TokenProvider;
import com.apprentice.app.service.interfaces.PostSeriesService;
import com.apprentice.app.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostSeriesController {

    private final TokenProvider tokenProvider;

    private final PostSeriesService postSeriesService;

    //GET
    @GetMapping("/series")
    public ResponseEntity<Object> series(HttpServletRequest request) {
        String writer = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();

        List<PostSeriesResponseDto> selectedList = postSeriesService.searchPostSeriesByWriter(writer);

        if (selectedList == null || selectedList.isEmpty()) return ResponseEntity.badRequest().body("nodata");
        return ResponseEntity.ok().body(selectedList);
    }

    @GetMapping("/series/{id}")
    public ResponseEntity<Object> series_id(@PathVariable int id) {
        PostSeriesResponseDto selected = postSeriesService.searchPostSeriesById(id);

        if (selected == null) return ResponseEntity.badRequest().body("nodata");
        return ResponseEntity.ok().body(selected);
    }

    //POST
    @PostMapping("/series")
    public ResponseEntity<Object> series_insert(@RequestBody PostSeriesRequestDto reqDto, HttpServletRequest request) {
        if (!reqDto.isValid()) {
            return ResponseEntity.badRequest().body("시리즈명은 필수 입력사항입니다.");
        }

        String writer = tokenProvider.getAuthentication(TokenUtil.resolveToken(request)).getName();
        reqDto.setWriter(writer);

        int id = postSeriesService.createPostSeries(reqDto);
        if (id == 0) {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        } else {
            return ResponseEntity.ok().body("ok");
        }
    }
}
