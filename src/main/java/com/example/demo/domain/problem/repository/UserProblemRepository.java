package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.entity.UserProblem;
import com.example.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {
    @Query("SELECT up FROM UserProblem up WHERE up.user = :user AND up.isCorrect = false")
    List<UserProblem> findWrongProblemsByUser(@Param("user") User user);

    int countByUserIdAndCommunityId(Long userId, Long communityId);


    @Query(value = """
    SELECT COUNT(*) + 1
    FROM (
        SELECT user_id, COUNT(*) as cnt
        FROM user_problem
        WHERE community_id = :communityId
        GROUP BY user_id
        HAVING cnt > (
            SELECT COUNT(*) FROM user_problem
            WHERE user_id = :userId AND community_id = :communityId
        )
    ) AS rank_table
""", nativeQuery = true)
    int getUserRankingInCommunity(@Param("userId") Long userId, @Param("communityId") Long communityId);

    List<UserProblem> findByUser(User user);
}
