package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.member.Member;
import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.domain.member.MemberResponseDto;
import com.apprentice.app.service.domain.token.TokenResponseDto;

public interface MemberService {
    //SELECT
    boolean isExistId(String id);
    TokenResponseDto login(MemberRequestDto reqDto);
    MemberRequestDto search(String id);
    //INSET
    String signUp(MemberRequestDto reqDto);
}
