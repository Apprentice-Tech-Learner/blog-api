package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.tag.PostTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    private String post_id;
    private String title;
    private String content;
    private String thumbnail;
    private String writer;
    private int status;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private int hits;
    private int likes;

    public PostResponseDto(Post entity) {
        this.post_id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.thumbnail = entity.getThumbnail();
        this.writer = entity.getWriter();
        this.status = entity.getStatus();
        this.description = entity.getDescription();
        this.created = entity.getCreated();
        this.updated = entity.getUpdated();
        this.hits = entity.getHits();
        this.likes = entity.getLikes();
    }
}
