package com.example.demo.domain.exam;

import com.example.demo.domain.category.entity.Category;
import com.example.demo.domain.category.entity.ExamCategory;
import com.example.demo.domain.category.repository.CategoryRepository;
import com.example.demo.domain.exam.dto.ExamJsonDTO;
import com.example.demo.domain.exam.entity.Exam;
import com.example.demo.domain.exam.repository.ExamRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExamInitializer {

    private final ExamRepository examRepository;
    private final CategoryRepository categoryRepository; // 🔥 카테고리 조회를 위해 필요

    @PostConstruct
    public void initExamData() {
        if (examRepository.count() > 0) return;

        try {
            InputStream is = new ClassPathResource("examData.json").getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<ExamJsonDTO> examList = mapper.readValue(is, new TypeReference<>() {});

            List<Exam> exams = examList.stream()
                    .map(dto -> {
                        ExamCategory type = ExamCategory.valueOf(dto.category());
                        Category category = categoryRepository.findByCategoryType(type)
                                .orElseThrow(() -> new RuntimeException("존재하지 않는 카테고리: " + dto.category()));

                        return Exam.builder()
                                .examName(dto.examName().toUpperCase())
                                .description(dto.description())
                                .category(category)
                                .build();
                    })
                    .toList();

            examRepository.saveAll(exams);
            System.out.println("자격증 시험 50개 초기화 완료");

        } catch (Exception e) {
            System.err.println("시험 데이터 초기화 실패: " + e.getMessage());
        }
    }
}
