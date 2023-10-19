package com.apprentice.app.service.domain.PostSeries;

import lombok.Getter;

@Getter
public class PostSeriesResponseDto {
    private int series_id;
    private String series_name;
    private int ord;

    public PostSeriesResponseDto(PostSeries entity) {
        this.series_id = entity.getSeries_id();
        this.series_name = entity.getSeries_name();
        this.ord = entity.getOrd();
    }
}
