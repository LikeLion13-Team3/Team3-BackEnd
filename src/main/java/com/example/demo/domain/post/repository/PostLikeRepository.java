package com.example.demo.domain.post.repository;

import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.entity.PostLike;
import com.example.demo.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostLikeRepository extends JpaRepository<Post, Long> {
//    Optional<PostLike> findByUserAndPost(User user, Post post);
//    void deleteByUserAndPost(User user, Post post);
//    boolean existsByUserAndPost(User user, Post post);
}
