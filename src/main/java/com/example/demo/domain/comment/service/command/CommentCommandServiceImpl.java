package com.example.demo.domain.comment.service.command;

import com.example.demo.domain.comment.converter.CommentConverter;
import com.example.demo.domain.comment.dto.CommentRequestDto.CommentRequestDto;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.exception.CommentErrorCode;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.repository.PostRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.apiPayload.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void createComment(Long postId, String loginId, CommentRequestDto.CommentCreateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .post(post)
                .user(user)
                .build();

        commentRepository.save(comment);
    }

    @Override
    public void updateComment(Long commentId, String loginId, CommentRequestDto.CommentUpdateRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getLoginId().equals(loginId)) {
            throw new CustomException(CommentErrorCode.COMMENT_FORBIDDEN);
        }

        CommentConverter.updateComment(comment, dto);
    }

}
