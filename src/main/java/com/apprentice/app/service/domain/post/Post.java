package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.common.BaseTimeEntity;
import com.apprentice.app.service.domain.postLike.PostLike;
import com.apprentice.app.service.domain.postSeries.PostSeries;
import com.apprentice.app.service.domain.tag.PostTag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
    @Column(length = 1)
    private int status;
    @Column(length = 400)
    private String description;
    @Column(length = 11)
    @Builder.Default
    private Integer hits = 0;
    private LocalDateTime updated;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series")
    private PostSeries series;

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
        this.series = dto.getSeries();
    }
}
