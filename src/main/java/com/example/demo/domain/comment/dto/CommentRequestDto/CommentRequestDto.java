package com.example.demo.domain.comment.dto.CommentRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentRequestDto {

    @Getter
    @NoArgsConstructor
    public static class CommentCreateRequestDto {

        @Schema(description = "댓글 내용", example = "이 글 정말 좋아요!")
        private String content;
    }

    @Getter
    @NoArgsConstructor
    public static class CommentUpdateRequestDto {

        @Schema(description = "댓글 내용", example = "수정된 댓글 내용")
        private String content;
    }
}
