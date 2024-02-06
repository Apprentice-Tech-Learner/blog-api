package com.apprentice.app.service.domain.postLike;

import com.apprentice.app.service.domain.member.Member;
import com.apprentice.app.service.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PostLikeId.class)
@Table(name = "post_like")
public class PostLike {
    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Id
    @ManyToOne
    @JoinColumn(name = "like_id")
    private Member member;
}
