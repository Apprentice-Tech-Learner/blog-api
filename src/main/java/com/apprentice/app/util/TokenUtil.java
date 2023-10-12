package com.apprentice.app.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {
    public static String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
