package com.example.demo.domain.exam.service.query;

import com.example.demo.domain.exam.dto.ExamResponseDTO.ExamResponseDTO;

import java.util.List;


public interface ExamQueryService {
    List<ExamResponseDTO.ExamResDTO> getExamsByCategoryId(Long categoryId);
}
