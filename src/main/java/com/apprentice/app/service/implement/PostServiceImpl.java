package com.apprentice.app.service.implement;

import com.apprentice.app.service.domain.post.*;
import com.apprentice.app.service.domain.tag.*;
import com.apprentice.app.service.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    @Override
    @Transactional
    public String writePost(PostRequestDto reqDto) {
        List<Tag> tagList = new ArrayList<>();
        for (String tag : reqDto.getTags()) {
            Tag data = tagRepository.findByName(tag);
            if (data == null) data = tagRepository.save(new TagRequestDto(tag).toEntity());

            tagList.add(data);
        }

        Post post = postRepository.save(reqDto.toEntity());
        for (Tag tag : tagList) {
            postTagRepository.save(new PostTagRequestDto(post, tag).toEntity());
        }

        return post.getPost_id();
    }

    @Override
    @Transactional
    public String editPost(String id, PostRequestDto reqDto) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        post.update(reqDto);

        List<Tag> tagList = new ArrayList<>();
        for (String tag : reqDto.getTags()) {
            Tag data = tagRepository.findByName(tag);
            if (data == null) data = tagRepository.save(new TagRequestDto(tag).toEntity());

            tagList.add(data);
        }

        // 없어진 tag 제거
        List<PostTag> oldPostTags = postTagRepository.findAllByPost(Post.builder().post_id(id).build());
        for (PostTag old : oldPostTags) {
            if (reqDto.getTags().contains(old.getTag().getName())) continue;
            postTagRepository.delete(old);
        }

        // 추가된 tag 입력
        for (Tag tag : tagList) {
            if (postTagRepository.existsById(new PostTagId(id, tag.getTag_id()))) continue;
            postTagRepository.save(new PostTagRequestDto(post, tag).toEntity());
        }

        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> searchPostSaves(int status) {
        return postRepository.findAllByStatus(status).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PostDetailResponseDto searchPostDetail(String id) {
        Post post = postRepository.findByIdUsingFetchJoin(id);
        return new PostDetailResponseDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto searchPost(String id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new PostResponseDto(post);
    }
}
