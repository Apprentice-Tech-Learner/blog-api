package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.post.PostDetailResponseDto;
import com.apprentice.app.service.domain.post.PostRequestDto;
import com.apprentice.app.service.domain.post.PostResponseDto;

import java.util.List;

public interface PostService {
    //INSERT
    String writePost(PostRequestDto reqDto);
    //UPDATE
    String editPost(String id, PostRequestDto reqDto);
    //SELECT
    List<PostResponseDto> searchPostSaves(int status);
    PostDetailResponseDto searchPostDetail(String id);
    PostResponseDto searchPost(String id);
}
