package com.example.demo.domain.post.dto.PostRequestDto;

public class PostRequestDto {

    @Getter
    @NoArgsConstructor
    public static class PostCreateRequestDto {

        @Schema(description = "게시글 제목", example = "게시글 제목")
        private String title;

        @Schema(description = "게시글 내용", example = "게시글 내용")
        private String content;
    }

}
