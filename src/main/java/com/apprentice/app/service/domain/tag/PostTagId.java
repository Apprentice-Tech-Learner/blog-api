package com.apprentice.app.service.domain.tag;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class PostTagId implements Serializable {
    private String post;
    private Long tag;

    public PostTagId(String post_id, Long tag_id) {
        this.post = post_id;
        this.tag = tag_id;
    }
}
