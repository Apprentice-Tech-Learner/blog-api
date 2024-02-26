package com.apprentice.app.service.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String id;
    private String name;
    private String email;
    private String contact;

    private String about_me;
    private String profile_image;

    public MemberResponseDto(Member entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.contact = entity.getContact();
        this.about_me = entity.getAbout_me();
        this.profile_image = entity.getProfile_image();
    }
}
