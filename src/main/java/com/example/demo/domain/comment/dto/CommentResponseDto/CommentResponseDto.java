package com.example.demo.domain.comment.dto.CommentResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


public class CommentResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CommentSimpleResponseDto {
        private Long commentId;
        private String content;
        private String writer;
        private String createdAt;
    }
}
