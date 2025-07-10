package com.example.demo.domain.community.repository;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.entity.UserCommunity;
import com.example.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunity, Long> {
    boolean existsByUserAndCommunity(User user, Community community);
    Optional<UserCommunity> findByUserIdAndCommunityId(Long userId, Long communityId);
}
