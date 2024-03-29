package com.apprentice.app.service.implement;

import com.apprentice.app.service.domain.postSeries.PostSeries;
import com.apprentice.app.service.domain.postSeries.PostSeriesRepository;
import com.apprentice.app.service.domain.postSeries.PostSeriesRequestDto;
import com.apprentice.app.service.domain.postSeries.PostSeriesResponseDto;
import com.apprentice.app.service.interfaces.PostSeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostSeriesServiceImpl implements PostSeriesService {

    private final PostSeriesRepository postSeriesRepository;

    @Override
    @Transactional
    public int createPostSeries(PostSeriesRequestDto reqDto) {
        PostSeries result = postSeriesRepository.save(reqDto.toEntity());
        return result.getSeries_id();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostSeriesResponseDto> searchPostSeriesByWriter(String writer) {
        List<PostSeries> res = postSeriesRepository.findAllByWriter(writer);
        if (res.isEmpty()) return null;

        return res.stream()
                .map(PostSeriesResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PostSeriesResponseDto searchPostSeriesById(int id) {
        PostSeries search = postSeriesRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 시리즈가 존재하지 않습니다."));
        return new PostSeriesResponseDto(search);
    }
}
