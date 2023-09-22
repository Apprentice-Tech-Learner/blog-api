package com.apprentice.app.service.interfaces;

import com.apprentice.app.service.domain.member.MemberRequestDto;
import com.apprentice.app.service.domain.member.MemberResponseDto;

public interface MemberService {
    boolean isExistId(String id);
    String signUp(MemberRequestDto reqDto);
}
