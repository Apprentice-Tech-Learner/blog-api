package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.tag.PostTag;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDetailResponseDto {
    private String post_id;
    private String title;
    private String content;
    private String thumbnail;
    private String writer;
    private int status;
    private String description;
    private LocalDateTime created;
    private List<PostTag> tags;

    public PostDetailResponseDto(Post entity) {
        this.post_id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.thumbnail = entity.getThumbnail();
        this.writer = entity.getWriter();
        this.status = entity.getStatus();
        this.description = entity.getDescription();
        this.created = entity.getCreated();
        this.tags = entity.getTags();
    }
}
