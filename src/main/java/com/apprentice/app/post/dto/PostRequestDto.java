package com.apprentice.app.post.dto;

import com.apprentice.app.post.entity.ApptechPost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRequestDto {
    private String title;
    private String content;
    private String userIdInsert;
    private char deleteYn;

    public ApptechPost toEntity() {
        return ApptechPost.builder()
                .title(title)
                .content(content)
                .userIdInsert(userIdInsert)
                .hits(0)
                .deleteYn(deleteYn)
                .build();
    }
}
