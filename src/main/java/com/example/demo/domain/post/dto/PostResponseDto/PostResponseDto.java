package com.example.demo.domain.post.dto.PostResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostResponseDto {


    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostCreateResponseDto {

        @Schema(description = "게시글 ID", example = "1")
        private Long postId;

        @Schema(description = "게시글 제목", example = "게시글 제목")
        private String title;

        @Schema(description = "게시글 내용", example = "게시글 내용")
        private String content;

        @Schema(description = "작성 유저 이름", example = "홍길동")
        private String writer;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PostSummaryResponseDto {

        @Schema(description = "게시글 ID", example = "1")
        private Long postId;

        @Schema(description = "게시글 제목", example = "스터디 모집 글")
        private String title;

        @Schema(description = "작성자 이름", example = "홍길동")
        private String writer;

        @Schema(description = "작성일", example = "2025-07-03T10:15:30")
        private LocalDateTime createdAt;
    }
}
