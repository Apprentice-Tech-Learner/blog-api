package com.apprentice.app.service.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String contact;

    @Builder
    public MemberRequestDto(String id, String password, String name, String email, String contact) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .contact(contact)
                .build();
    }
}
