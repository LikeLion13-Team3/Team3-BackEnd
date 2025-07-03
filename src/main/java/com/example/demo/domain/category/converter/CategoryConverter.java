package com.example.demo.domain.category.converter;

import com.example.demo.domain.category.dto.ExamResponseDTO.CategoryResponseDTO;
import com.example.demo.domain.category.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryResponseDTO.CategoryResDTO toResponse(Category category) {
        return new CategoryResponseDTO.CategoryResDTO(
                category.getId(),
                category.getCategoryType()
        );
    }
}