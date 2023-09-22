package com.apprentice.app.service.implement;

import com.apprentice.app.service.domain.member.Member;
import com.apprentice.app.service.domain.member.MemberRepository;
import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.domain.member.MemberResponseDto;
import com.apprentice.app.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isExistId(String id) {
        return memberRepository.existsById(id);
    }

    @Override
    public String signUp(MemberRequestDto reqDto) {
        return memberRepository.save(reqDto.toEntity()).getId();
    }
}
