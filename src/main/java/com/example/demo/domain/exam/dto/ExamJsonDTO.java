package com.example.demo.domain.exam.dto;

import com.example.demo.domain.category.entity.ExamCategory;

public record ExamJsonDTO(
        String examName,
        String description,
        String category
) {}