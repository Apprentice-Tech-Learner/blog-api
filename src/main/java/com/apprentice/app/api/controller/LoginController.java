package com.apprentice.app.api.controller;

import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.domain.token.TokenRequestDto;
import com.apprentice.app.service.domain.token.TokenResponseDto;
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

        if (id == null || id.isEmpty()) {
            return ResponseEntity.badRequest().body("입력에 실패하였습니다. 잠시 후 다시 이용해주세요.");
        } else {
            return ResponseEntity.ok()
                    .body(memberService.login(reqDto));
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validate(@RequestBody MemberRequestDto reqDto) {
        boolean isExist = memberService.isExistId(reqDto.getId());
        if (!isExist) {
            return ResponseEntity.status(403).body("아이디를 다시 확인해주세요.");
        }

        TokenResponseDto result = memberService.login(reqDto);
        if (result == null) {
            return ResponseEntity.status(401).body("계정정보가 일치하지 않습니다.");
        } else {
            return ResponseEntity.ok()
                    .body(result);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody TokenRequestDto reqDto) {
        TokenResponseDto result = memberService.login(memberService.search(reqDto.getUser_id()));
        if (result == null) {
            return ResponseEntity.status(401).body("유효하지 않은 아이디입니다.");
        } else {
            return ResponseEntity.ok()
                    .body(result);
        }
    }
}
