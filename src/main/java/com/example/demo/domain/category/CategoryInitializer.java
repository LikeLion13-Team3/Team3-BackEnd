package com.example.demo.domain.category;

import com.example.demo.domain.category.entity.Category;
import com.example.demo.domain.category.entity.ExamCategory;
import com.example.demo.domain.category.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryInitializer {

    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void initCategories() {
        if (categoryRepository.count() > 0) return;

        for (ExamCategory type : ExamCategory.values()) {
            categoryRepository.save(
                    Category.builder()
                            .categoryType(type)
                            .build()
            );
        }
        log.info("카테고리 초기화 완료");
    }
}
