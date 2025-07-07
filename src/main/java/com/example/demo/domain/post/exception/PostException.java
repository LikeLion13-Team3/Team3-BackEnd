package com.example.demo.domain.post.exception;

import com.example.demo.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class PostException extends CustomException {

    public PostException(PostErrorCode postErrorCode) {
        super(postErrorCode);
    }
}
