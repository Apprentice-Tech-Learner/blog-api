package com.apprentice.app.service.domain.postLike;

import com.apprentice.app.service.domain.member.Member;
import com.apprentice.app.service.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostLikeRequestDto {
    private String post_id;
    private String member_id;

    public PostLike toEntity() {
        return PostLike.builder()
                .post(Post.builder().post_id(post_id).build())
                .member(Member.builder().id(member_id).build())
                .build();
    }
}
