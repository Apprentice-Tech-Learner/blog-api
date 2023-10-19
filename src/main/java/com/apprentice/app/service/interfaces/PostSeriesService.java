package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.PostSeries.PostSeriesRequestDto;
import com.apprentice.app.service.domain.PostSeries.PostSeriesResponseDto;

import java.util.List;

public interface PostSeriesService {
    //INSERT
    int createPostSeries(PostSeriesRequestDto reqDto);
    //SELECT
    List<PostSeriesResponseDto> searchPostSeriesByWriter(String writer);
    PostSeriesResponseDto searchPostSeriesById(int id);
}
