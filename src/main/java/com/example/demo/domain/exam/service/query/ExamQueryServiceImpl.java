package com.example.demo.domain.exam.service.query;


import com.example.demo.domain.exam.converter.ExamConverter;
import com.example.demo.domain.exam.dto.ExamResponseDTO.ExamResponseDTO;
import com.example.demo.domain.exam.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamQueryServiceImpl implements ExamQueryService {

    private final ExamRepository examRepository;
    private final ExamConverter examConverter;

    @Override
    public List<ExamResponseDTO.ExamResDTO> getAllExams() {

        return examRepository.findAll().stream()
                .map(examConverter::toResponse)
                .collect(Collectors.toList());
    }
}