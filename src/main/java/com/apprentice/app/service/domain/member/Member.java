package com.apprentice.app.service.domain.member;

import com.apprentice.app.service.domain.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    private String contact;

    @Builder
    public Member(String id, String password, String name, String email, String contact) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }
}
