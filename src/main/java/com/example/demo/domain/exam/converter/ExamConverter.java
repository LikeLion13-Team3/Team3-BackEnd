package com.example.demo.domain.exam.converter;

import com.example.demo.domain.exam.dto.ExamResponseDTO.ExamResponseDTO;
import com.example.demo.domain.exam.entity.Exam;
import org.springframework.stereotype.Component;

@Component
public class ExamConverter {

    public ExamResponseDTO.ExamResDTO toResponse(Exam exam) {
        return new ExamResponseDTO.ExamResDTO(
                exam.getId(),
                exam.getExamName(),
                exam.getDescription()
        );
    }
}
