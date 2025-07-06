package com.example.demo.domain.exam.service.query;


import com.example.demo.domain.exam.dto.ExamResponseDTO.ExamResponseDTO;
import com.example.demo.domain.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamQueryServiceImpl implements ExamQueryService {
    private final ExamRepository examRepository;

    @Override
    public List<ExamResponseDTO.ExamResDTO> getExamsByCategoryId(Long categoryId) {
        return examRepository.findByCategoryId(categoryId).stream()
                .map(exam -> new ExamResponseDTO.ExamResDTO(
                        exam.getId(),
                        exam.getExamName(),
                        exam.getDescription()
                )).toList();
    }

}