package com.apprentice.app.filter;

import com.apprentice.app.exception.TokenNotValidateException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenNotValidateException e) {
            setErrorResponse(HttpStatus.FORBIDDEN, request, response, e);
            return;
        }
    }

    private void setErrorResponse(HttpStatus status, HttpServletRequest request, HttpServletResponse response, Throwable e) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.getWriter().write(new ErrorResponse
                (
                        HttpStatus.UNAUTHORIZED,
                        e.getMessage()
                )
                .convertToJson()
        );
    }

    @Getter
    public static class ErrorResponse{

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private final String timestamp;
        private final int status;
        private final String error;
        private final String message;

        //생성자 및 정적 메소드 생략
        public ErrorResponse(HttpStatus status, String message) {
            this.timestamp = new Date().toString();
            this.status = status.value();
            this.error = status.getReasonPhrase();
            this.message = message;
        }

        public String convertToJson() throws JsonProcessingException {
            return objectMapper.writeValueAsString(this);
        }
    }
}
