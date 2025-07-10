package com.example.demo.domain.problem.service.query;


import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemQueryServiceImpl implements ProblemQueryService {

    @Override
    public ProblemResponseDto getRandomProblemFromGpt(String topic) {
        return null;
    }
}