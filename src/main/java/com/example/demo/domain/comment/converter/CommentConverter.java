package com.example.demo.domain.comment.converter;

import com.example.demo.domain.comment.dto.CommentRequestDto.CommentRequestDto;
import com.example.demo.domain.comment.dto.CommentResponseDto.CommentResponseDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.user.entity.User;

public class CommentConverter {

    public static Comment toEntity(CommentRequestDto.CommentCreateRequestDto dto, User user, Post post) {
        return Comment.builder()
                .content(dto.getContent())
                .user(user)
                .post(post)
                .build();
    }

    public static void updateComment(Comment comment, CommentRequestDto.CommentUpdateRequestDto dto) {
        comment.updateContent(dto.getContent());
    }

    public static CommentResponseDto.CommentSimpleResponseDto toCommentDetailResponseDto(Comment comment) {
        return CommentResponseDto.CommentSimpleResponseDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getUser().getUsername())
                .createdAt(String.valueOf(comment.getCreatedAt()))
                .build();
    }

    public static CommentResponseDto.CommentSimpleResponseDto toSimpleResponseDto(Comment comment) {
        return CommentResponseDto.CommentSimpleResponseDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getUser().getUsername())
                .createdAt(comment.getCreatedAt().toString())
                .build();
    }
}
