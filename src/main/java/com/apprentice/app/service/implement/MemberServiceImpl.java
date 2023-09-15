package com.apprentice.app.service.implement;

import com.apprentice.app.service.domain.member.MemberRepository;
import com.apprentice.app.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
}
