package com.apprentice.app.service.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String id;

    public MemberResponseDto(String id) {
        this.id = id;
    }
}
