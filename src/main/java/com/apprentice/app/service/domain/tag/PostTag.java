package com.apprentice.app.service.domain.tag;

import com.apprentice.app.service.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PostTagId.class)
@Table(name = "post_tag")
public class PostTag {
    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Id
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
