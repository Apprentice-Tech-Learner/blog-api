package com.apprentice.app.api.controller;

import com.apprentice.app.post.dto.PostRequestDto;
import com.apprentice.app.post.dto.PostResponseDto;
import com.apprentice.app.post.model.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostApiController {
    private final PostService postService;

    @PostMapping("/posts")
    public Long save(@RequestBody final PostRequestDto params) {
        return postService.save(params);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> findAll() {
        return postService.findAll();
    }

    @PatchMapping("/posts/{seq}")
    public Long save(@PathVariable final Long seq, @RequestBody final PostRequestDto params) {
        return postService.update(seq, params);
    }
}
