package com.example.demo.domain.comment.service.query;


import com.example.demo.domain.comment.converter.CommentConverter;
import com.example.demo.domain.comment.dto.CommentResponseDto.CommentResponseDto;
import com.example.demo.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.domain.comment.entity.Comment;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository commentRepository;

    @Override
    public List<CommentResponseDto.CommentSimpleResponseDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream()
                .map(CommentConverter::toSimpleResponseDto)
                .collect(Collectors.toList());
    }
}