package com.apprentice.app.service.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String contact;
}
