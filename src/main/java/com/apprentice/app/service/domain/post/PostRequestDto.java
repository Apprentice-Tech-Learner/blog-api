package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.postSeries.PostSeries;
import com.apprentice.app.util.UUIDGenerator;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private String thumbnail;
    private String writer;
    private List<String> tags;
    private Integer series_id;
    private PostSeries series;
    private int status;
    private String description;

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public void setSeries(PostSeries series) { this.series = series; }
    public boolean isValid() {
        if (title == null || title.isEmpty()) return false;
        if (content == null || content.isEmpty()) return false;
        return true;
    }
    public Post toEntity() {
        return Post.builder()
                .post_id(UUIDGenerator.createUUID())
                .title(title)
                .content(content)
                .thumbnail(thumbnail)
                .writer(writer)
                .status(status)
                .description(description)
                .series(series)
                .updated(LocalDateTime.now())
                .build();
    }
}
