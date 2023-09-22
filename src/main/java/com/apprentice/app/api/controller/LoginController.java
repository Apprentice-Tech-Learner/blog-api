package com.apprentice.app.api.controller;

import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody MemberRequestDto reqDto) {
        String id = memberService.signUp(reqDto);

        if (id == null || id.isEmpty()) return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        else return ResponseEntity.ok().body(id.concat(" (이)가 입력 완료되었습니다."));
    }
}
