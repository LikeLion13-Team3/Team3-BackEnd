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
}
