package com.example.demo.domain.problem.service.query;

import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;

public interface ProblemQueryService {
    ProblemResponseDto getRandomProblemFromGpt(String topic);
}
