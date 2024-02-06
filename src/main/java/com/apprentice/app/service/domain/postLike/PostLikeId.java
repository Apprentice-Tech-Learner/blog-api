package com.apprentice.app.service.domain.postLike;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class PostLikeId implements Serializable {
    private String post;
    private String member;

    public PostLikeId(String post_id, String member_id) {
        this.post = post_id;
        this.member = member_id;
    }
}
