package com.apprentice.app.service.implement;

import com.apprentice.app.service.domain.member.MemberRepository;
import com.apprentice.app.service.domain.member.MemberResponseDto;
import com.apprentice.app.service.domain.post.*;
import com.apprentice.app.service.domain.postLike.PostLike;
import com.apprentice.app.service.domain.postLike.PostLikeId;
import com.apprentice.app.service.domain.postLike.PostLikeRepository;
import com.apprentice.app.service.domain.postLike.PostLikeRequestDto;
import com.apprentice.app.service.domain.postSeries.PostSeries;
import com.apprentice.app.service.domain.postSeries.PostSeriesRepository;
import com.apprentice.app.service.domain.postSeries.PostSeriesResponseDto;
import com.apprentice.app.service.domain.tag.*;
import com.apprentice.app.service.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final MemberRepository memberRepository;
    private final PostTagRepository postTagRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostSeriesRepository postSeriesRepository;

    //DELETE
    @Override
    @Transactional
    public int deletePost(String id) {
        return 0;
    }

    @Override
    @Transactional
    public int deletePostLike(PostLikeId id) {
        postLikeRepository.deleteById(id);

        return postLikeRepository.existsById(id) ? 0 : 1;
    }

    //INSERT
    @Override
    @Transactional
    public String writePost(PostRequestDto reqDto) {
        List<Tag> tagList = new ArrayList<>();
        for (String tag : reqDto.getTags()) {
            Tag data = tagRepository.findByName(tag);
            if (data == null) data = tagRepository.save(new TagRequestDto(tag).toEntity());

            tagList.add(data);
        }

        if (reqDto.getSeries_id() != null) {
            Optional<PostSeries> series = postSeriesRepository.findById(reqDto.getSeries_id());
            series.ifPresent(reqDto::setSeries);
        }

        log.info("시리즈 검색 통과");

        Post post = postRepository.save(reqDto.toEntity());
        for (Tag tag : tagList) {
            postTagRepository.save(new PostTagRequestDto(post, tag).toEntity());
        }

        return post.getPost_id();
    }

    @Override
    @Transactional
    public String writePostLike(PostLikeRequestDto reqDto) {
        PostLike postLike = postLikeRepository.save(reqDto.toEntity());

        return postLike.getPost().getPost_id();
    }

    //UPDATE
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

    //SELECT
    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> searchPostByUpdatedPaging(LocalDateTime from, LocalDateTime to, PageRequest request) {
        List<Post> selected = postRepository.findSliceByUpdatedBetweenAndStatus(from, to, request, 1)
                .stream()
                .collect(Collectors.toList());

        List<PostResponseDto> result = selected
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return result;
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
    public PostDetailResponseDto searchPostDetail(String id, String user) {
        Post post = postRepository.findByIdUsingFetchJoin(id);

        PostDetailResponseDto result = new PostDetailResponseDto(post);

        //좋아요 정보
        long likeCount = postLikeRepository.countByPost(post);
        boolean isLiked = postLikeRepository.existsById(new PostLikeId(post.getPost_id(), user));
        result.setLikeInfo(likeCount, isLiked);

        //시리즈 정보
        if (post.getSeries() != null) {
            PostSeries ps = postSeriesRepository.findById(post.getSeries().getSeries_id()).get();
            PostSeriesResponseDto series = new PostSeriesResponseDto(ps);
            List<PostResponseDto> seriesList = postRepository.findAllBySeries(ps).stream().map(PostResponseDto::new).collect(Collectors.toList());
            series.setSeriesList(seriesList);
            result.setPostSeries(series);
        }

        //작성자 정보
        MemberResponseDto postUser = new MemberResponseDto(memberRepository.findById(post.getWriter()).get());
        result.setUser(postUser);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto searchPost(String id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new PostResponseDto(post);
    }
}
