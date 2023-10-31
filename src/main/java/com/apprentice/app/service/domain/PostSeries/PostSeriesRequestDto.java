package com.apprentice.app.service.domain.PostSeries;

import lombok.*;

@Getter
@ToString
public class PostSeriesRequestDto {
    private String series_name;
    private String writer;

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public boolean isValid() {
        if (series_name == null || series_name.isEmpty()) return false;
        return true;
    }
    public PostSeries toEntity() {
        return PostSeries.builder()
                .series_name(series_name)
                .writer(writer)
                .build();
    }
}
