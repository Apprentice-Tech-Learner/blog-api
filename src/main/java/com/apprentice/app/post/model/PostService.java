package com.apprentice.app.post.model;

import java.util.List;
import java.util.stream.Collectors;

import com.apprentice.app.exception.CustomException;
import com.apprentice.app.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apprentice.app.post.dto.PostRequestDto;
import com.apprentice.app.post.dto.PostResponseDto;
import com.apprentice.app.post.entity.ApptechPost;
import com.apprentice.app.post.entity.ApptechPostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ApptechPostRepository postRepository;

    @Transactional
    public Long save(final PostRequestDto params) {
        ApptechPost entity = postRepository.save(params.toEntity());
        return entity.getSeq();
    }

    public List<PostResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "seq", "insertDate");
        List<ApptechPost> list = postRepository.findAll(sort);
        return list.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(final Long seq, final PostRequestDto params) {
        ApptechPost entity = postRepository.findById(seq).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getUserIdInsert());
        return seq;
    }
}
