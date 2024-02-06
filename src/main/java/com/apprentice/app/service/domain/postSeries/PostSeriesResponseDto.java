package com.apprentice.app.service.domain.postSeries;

import lombok.Getter;

@Getter
public class PostSeriesResponseDto {
    private int series_id;
    private String series_name;

    public PostSeriesResponseDto(PostSeries entity) {
        this.series_id = entity.getSeries_id();
        this.series_name = entity.getSeries_name();
    }
}
