package com.example.demo.domain.comment.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Comment404", "댓글을 찾을 수 없습니다"),
    COMMENT_FORBIDDEN(HttpStatus.FORBIDDEN, "Comment403", "댓글 수정 권한이 없습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
