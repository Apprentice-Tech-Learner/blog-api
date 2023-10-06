package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.member.Member;
import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.domain.member.MemberResponseDto;
import com.apprentice.app.service.domain.token.TokenResponseDto;

public interface MemberService {
    boolean isExistId(String id);
    String signUp(MemberRequestDto reqDto);
    TokenResponseDto login(MemberRequestDto reqDto);
}
