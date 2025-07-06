package com.example.demo.domain.problem.repository;

import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.UserProblem;
import com.example.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByMissionId(Long missionId);

    // UserProblem 관련 쿼리
    @Query("SELECT up FROM UserProblem up WHERE up.user = :user AND up.isCorrect = false")
    List<UserProblem> findWrongProblemsByUser(@Param("user") User user);

    @Query("SELECT COUNT(up) FROM UserProblem up WHERE up.user = :user AND up.problem.mission.community.id = :communityId")
    int countUserSolvedProblemsInCommunity(@Param("user") User user, @Param("communityId") Long communityId);
}
