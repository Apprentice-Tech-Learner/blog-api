package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.common.BaseTimeEntity;
import com.apprentice.app.service.domain.postLike.PostLike;
import com.apprentice.app.service.domain.tag.PostTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post extends BaseTimeEntity {
    @Id
    @Column(name = "post_id", unique = true, nullable = false)
    private String post_id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(length = 2048)
    private String thumbnail;
    @Column(length = 50)
    private String writer;
    private int status;
    @Column(length = 400)
    private String description;
    private int series;
    private int hits;
    private LocalDateTime updated;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostTag> tags = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostLike> likes = new ArrayList<>();

    public void update(PostRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.thumbnail = dto.getThumbnail();
        this.status = dto.getStatus();
        this.description = dto.getDescription();
        this.updated = LocalDateTime.now();
        this.series = Integer.parseInt(dto.getSeries_id());
    }
}
