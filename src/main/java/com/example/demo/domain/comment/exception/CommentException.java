package com.example.demo.domain.comment.exception;

import com.example.demo.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class CommentException extends CustomException {

    public CommentException(CommentErrorCode commentErrorCode) {
        super(commentErrorCode);
    }
}
