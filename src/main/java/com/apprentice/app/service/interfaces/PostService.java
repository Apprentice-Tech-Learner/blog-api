package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.post.PostDetailResponseDto;
import com.apprentice.app.service.domain.post.PostRequestDto;
import com.apprentice.app.service.domain.post.PostResponseDto;
import com.apprentice.app.service.domain.postLike.PostLikeId;
import com.apprentice.app.service.domain.postLike.PostLikeRequestDto;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    //DELETE
    int deletePost(String id);
    int deletePostLike(PostLikeId id);
    //INSERT
    String writePost(PostRequestDto reqDto);
    String writePostLike(PostLikeRequestDto reqDto);
    //UPDATE
    String editPost(String id, PostRequestDto reqDto);
    //SELECT
    List<PostResponseDto> searchPostByUpdatedPaging(LocalDateTime from, LocalDateTime to, PageRequest request);
    List<PostResponseDto> searchPostSaves(int status);
    PostDetailResponseDto searchPostDetail(String id, String user);
    PostResponseDto searchPost(String id);
}
