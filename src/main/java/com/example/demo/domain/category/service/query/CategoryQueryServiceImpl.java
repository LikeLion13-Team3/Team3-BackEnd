package com.example.demo.domain.category.service.query;


import com.example.demo.domain.category.converter.CategoryConverter;
import com.example.demo.domain.category.dto.ExamResponseDTO.CategoryResponseDTO;
import com.example.demo.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public List<CategoryResponseDTO.CategoryResDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryConverter::toResponse)
                .collect(Collectors.toList());
    }
}