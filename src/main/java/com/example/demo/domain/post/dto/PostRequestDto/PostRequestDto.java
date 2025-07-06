package com.example.demo.domain.post.dto.PostRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostRequestDto {

    @Getter
    @NoArgsConstructor
    public static class PostCreateRequestDto {

        @Schema(description = "게시글 제목", example = "게시글 제목")
        private String title;

        @Schema(description = "게시글 내용", example = "게시글 내용")
        private String content;
    }

    @Getter
    @NoArgsConstructor
    public static class PostUpdateRequestDto {

        @Schema(description = "게시글 제목", example = "수정된 제목")
        private String title;

        @Schema(description = "게시글 내용", example = "수정된 내용")
        private String content;
    }
}
