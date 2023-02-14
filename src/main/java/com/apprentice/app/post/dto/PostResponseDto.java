package com.apprentice.app.post.dto;

import com.apprentice.app.post.entity.ApptechPost;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long seq;
    private String title;
    private String content;
    private int hits;
    private char deleteYn;
    private LocalDateTime insertDate;
    private String userIdInsert;
    private LocalDateTime updateDate;
    private String userIdUpdate;

    public PostResponseDto(ApptechPost entity) {
        this.seq = entity.getSeq();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.hits = entity.getHits();
        this.deleteYn = entity.getDeleteYn();
        this.insertDate = entity.getInsertDate();
        this.userIdInsert = entity.getUserIdInsert();
        this.updateDate = entity.getUpdateDate();
        this.userIdUpdate = entity.getUserIdUpdate();
    }
}
