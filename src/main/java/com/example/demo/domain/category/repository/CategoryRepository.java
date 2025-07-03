package com.example.demo.domain.category.repository;

import com.example.demo.domain.category.entity.Category;
import com.example.demo.domain.category.entity.ExamCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryType(ExamCategory categoryType);
}

