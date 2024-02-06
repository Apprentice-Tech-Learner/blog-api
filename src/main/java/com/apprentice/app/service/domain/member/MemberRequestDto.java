package com.apprentice.app.service.domain.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String contact;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .contact(contact)
                .updated(LocalDateTime.now())
                .build();
    }
}
