package com.apprentice.app.service.domain.postSeries;

import com.apprentice.app.service.domain.post.PostResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class PostSeriesResponseDto {
    private int series_id;
    private String series_name;
    private List<PostResponseDto> series_list;

    public PostSeriesResponseDto(PostSeries entity) {
        this.series_id = entity.getSeries_id();
        this.series_name = entity.getSeries_name();
    }

    public void setSeriesList(List<PostResponseDto> list) {
        series_list = list;
    }
}
