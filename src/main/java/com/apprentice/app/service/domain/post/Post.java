package com.apprentice.app.service.domain.post;

import com.apprentice.app.service.domain.common.BaseTimeEntity;
import com.apprentice.app.service.domain.tag.PostTag;
import com.apprentice.app.service.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    private Integer series;
    @Column(length = 400)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostTag> tags = new ArrayList<>();

    public void update(PostRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.thumbnail = dto.getThumbnail();
        this.status = dto.getStatus();
        this.description = dto.getDescription();
    }
}
