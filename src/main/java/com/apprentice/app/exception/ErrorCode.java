package com.apprentice.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."), // 400
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "게시글 정보를 찾을 수 없습니다."), // 404
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."), // 405
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."), // 500
    TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "Expired token"), // 403
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"), // 401
    ;

    private final HttpStatus status;
    private final String message;
}
