package com.example.demo.domain.comment.repository;

import com.example.demo.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUserId(Long commentId, Long userId);
    List<Comment> findAllByPostId(Long postId);
}