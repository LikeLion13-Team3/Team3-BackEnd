package com.example.demo.domain.category.controller;

import com.example.demo.domain.category.dto.ExamResponseDTO.CategoryResponseDTO;
import com.example.demo.domain.category.service.query.CategoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "카테고리", description = "카테고리 관련 API")
public class CategoryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping
    @Operation(summary = "전체 카테고리 조회")
    public ResponseEntity<List<CategoryResponseDTO.CategoryResDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryQueryService.getAllCategories());
    }
}