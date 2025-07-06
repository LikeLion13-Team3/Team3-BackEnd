package com.example.demo.domain.community.repository;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    boolean existsByExam(Exam exam);
    Optional<Community> findByExam(Exam exam);
}
