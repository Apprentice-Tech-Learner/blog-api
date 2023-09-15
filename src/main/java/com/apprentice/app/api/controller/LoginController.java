package com.apprentice.app.api.controller;

import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> find(@PathVariable String id) {
        boolean isExist = memberService.isExistId(id);

        if (isExist) return ResponseEntity.status(409).body("이미 존재하는 아이디입니다.");
        return ResponseEntity.ok().body("생성 가능한 아이디입니다.");
    }
}
