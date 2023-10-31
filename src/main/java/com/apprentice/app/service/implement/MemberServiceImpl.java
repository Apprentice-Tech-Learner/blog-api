package com.apprentice.app.service.implement;

import com.apprentice.app.service.domain.member.Member;
import com.apprentice.app.service.domain.member.MemberRepository;
import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.domain.member.MemberResponseDto;
import com.apprentice.app.service.domain.token.TokenProvider;
import com.apprentice.app.service.domain.token.TokenResponseDto;
import com.apprentice.app.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Override
    @Transactional(readOnly = true)
    public boolean isExistId(String id) {
        return memberRepository.existsById(id);
    }

    @Override
    @Transactional
    public String signUp(MemberRequestDto reqDto) {
        return memberRepository.save(reqDto.toEntity()).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public TokenResponseDto login(MemberRequestDto reqDto) {
        //authenticated 가 false인 token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(reqDto.getId(), reqDto.getPassword());
        //실제 검증, authenticate 메서드 실행시 CustomUserDetailService에서 만든 loadByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return tokenProvider.generateJWT(authentication);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberRequestDto search(String id) {
        Member mem = memberRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        return MemberRequestDto.builder()
                .id(mem.getId())
                .password(mem.getPassword())
                .build();
    }
}
