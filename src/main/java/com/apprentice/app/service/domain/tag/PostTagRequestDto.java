package com.apprentice.app.service.domain.tag;

import com.apprentice.app.service.domain.post.Post;

public class PostTagRequestDto {
    private Post post;
    private Tag tag;

    public PostTagRequestDto(Post post, Tag tag) {
        this.post = post;
        this.tag = tag;
    }

    public PostTag toEntity() {
        return PostTag.builder()
                .post(post)
                .tag(tag)
                .build();
    }
}
