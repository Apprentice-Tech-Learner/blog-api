package com.apprentice.app.post.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApptechPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;

    private String content;

    private int hits;

    private char deleteYn;

    private LocalDateTime insertDate = LocalDateTime.now();

    private String userIdInsert;

    private LocalDateTime updateDate;

    private String userIdUpdate;

    @Builder
    public ApptechPost(String title, String content, String userIdInsert, int hits, char deleteYn) {
        this.title = title;
        this.content = content;
        this.userIdInsert = userIdInsert;
        this.hits = hits;
        this.deleteYn = deleteYn;
    }

    public void update(String title, String content, String userIdUpdate) {
        this.title = title;
        this.content = content;
        this.userIdUpdate = userIdUpdate;
        this.updateDate = LocalDateTime.now();
    }
}
