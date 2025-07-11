package com.example.demo.domain.comment.service.query;

import com.example.demo.domain.comment.dto.CommentResponseDto.CommentResponseDto;
import com.example.demo.domain.exam.dto.ExamResponseDTO.ExamResponseDTO;

import java.util.List;

public interface CommentQueryService {
    List<CommentResponseDto.CommentSimpleResponseDto> getCommentsByPostId(Long postId);
}
