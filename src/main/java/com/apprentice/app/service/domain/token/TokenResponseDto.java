package com.apprentice.app.service.domain.token;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class TokenResponseDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
