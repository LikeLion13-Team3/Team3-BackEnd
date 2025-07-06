package com.example.demo.domain.post.dto.PostResponseDto;

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
