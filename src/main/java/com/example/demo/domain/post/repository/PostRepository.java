package com.example.demo.domain.post.repository;

import com.example.demo.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByCommunityId(Long communityId, Pageable pageable);
    Optional<Post> findByIdAndCommunityId(Long postId, Long communityId);

}
