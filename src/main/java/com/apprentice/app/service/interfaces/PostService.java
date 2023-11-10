package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.post.PostDetailResponseDto;
import com.apprentice.app.service.domain.post.PostRequestDto;
import com.apprentice.app.service.domain.post.PostResponseDto;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    //INSERT
    String writePost(PostRequestDto reqDto);
    //UPDATE
    String editPost(String id, PostRequestDto reqDto);
    //SELECT
    List<PostResponseDto> searchPostByUpdatedPaging(LocalDateTime from, LocalDateTime to, PageRequest request);
    List<PostResponseDto> searchPostSaves(int status);
    PostDetailResponseDto searchPostDetail(String id);
    PostResponseDto searchPost(String id);
}
