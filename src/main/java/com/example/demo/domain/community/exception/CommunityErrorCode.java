package com.example.demo.domain.community.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum CommunityErrorCode implements BaseErrorCode {

    COMMUNITY_NOT_FOUND(HttpStatus.NOT_FOUND, "C404", "커뮤니티를 찾을 수 없습니다."),
    NOT_PARTICIPATED(HttpStatus.FORBIDDEN, "C403", "해당 커뮤니티에 참여하지 않았습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    CommunityErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}