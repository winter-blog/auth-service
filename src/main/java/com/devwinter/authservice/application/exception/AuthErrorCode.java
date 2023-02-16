package com.devwinter.authservice.application.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    MEMBER_PASSWORD_NOT_VALID(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    LOGOUT_ALREADY_STATUS(HttpStatus.NOT_FOUND, "이미 로그아웃된 상태입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
