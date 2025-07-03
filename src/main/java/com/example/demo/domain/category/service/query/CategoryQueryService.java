package com.example.demo.domain.category.service.query;


import com.example.demo.domain.category.dto.ExamResponseDTO.CategoryResponseDTO;

import java.util.List;

public interface CategoryQueryService {
    List<CategoryResponseDTO.CategoryResDTO> getAllCategories();
}
