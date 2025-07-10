package com.example.demo.domain.exam.controller;

import com.example.demo.domain.exam.dto.ExamResponseDTO.ExamResponseDTO;
import com.example.demo.domain.exam.service.query.ExamQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "시험", description = "시험 관련 API")
public class ExamController {

    private final ExamQueryService examQueryService;

    @GetMapping("/api/categories/{categoryId}/exams")
    @Operation(summary = "특정 카테고리의 시험 전체 조회")
    public ResponseEntity<List<ExamResponseDTO.ExamResDTO>> getExamsByCategory(@PathVariable Long categoryId) {
        List<ExamResponseDTO.ExamResDTO> exams = examQueryService.getExamsByCategoryId(categoryId);
        return ResponseEntity.ok(exams);
    }
}