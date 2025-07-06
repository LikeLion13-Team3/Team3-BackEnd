package com.example.demo.domain.exam.repository;

import com.example.demo.domain.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> findByCategoryId(Long categoryId);
}