package com.example.demo.domain.post.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post404", "게시글을 찾을 수 없습니다"),
    POST_FORBIDDEN(HttpStatus.FORBIDDEN, "Post403", "게시글 수정/삭제 권한이 없습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;}
