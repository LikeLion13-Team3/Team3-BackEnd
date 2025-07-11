package com.example.demo.global.apiPayload.code;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Common
    COMMON_INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON400", "유효하지 않은 파라미터입니다."),
    COMMON_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 내부 오류가 발생했습니다."),

    // User specific
    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "USER409", "이미 존재하는 아이디입니다."), // <<<--- 이 부분 추가
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "USER401", "비밀번호가 일치하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}