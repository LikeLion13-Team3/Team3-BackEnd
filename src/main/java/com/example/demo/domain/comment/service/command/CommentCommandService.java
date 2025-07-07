package com.example.demo.domain.comment.service.command;

import com.example.demo.domain.comment.dto.CommentRequestDto.CommentRequestDto;

public interface CommentCommandService {

    void createComment(Long postId, String loginId, CommentRequestDto.CommentCreateRequestDto requestDto);

    void updateComment(Long commentId, String loginId, CommentRequestDto.CommentUpdateRequestDto dto);

    void deleteComment(Long commentId, String loginId);
}
