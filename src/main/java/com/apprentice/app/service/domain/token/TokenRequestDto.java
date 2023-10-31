package com.apprentice.app.service.domain.token;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDto {
    private String user_id;
}
