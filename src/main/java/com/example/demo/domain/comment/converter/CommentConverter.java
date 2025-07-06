package com.example.demo.domain.comment.converter;

public class CommentConverter {
    public static CommentResponseDto.CommentSimpleResponseDto toSimpleResponseDto(Comment comment) {
        return CommentResponseDto.CommentSimpleResponseDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt().toString())
                .build();
    }
}
