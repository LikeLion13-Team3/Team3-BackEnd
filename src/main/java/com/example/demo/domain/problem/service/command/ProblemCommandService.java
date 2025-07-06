package com.example.demo.domain.problem.service.command;


import com.example.demo.domain.problem.dto.ProblemRequestDto.ProblemRequestDto;
import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import com.example.demo.global.dto.ApiResponse;

import java.util.List;

public interface ProblemCommandService {
    ApiResponse<ProblemResponseDto.ProblemResponse> getRandomProblem(Long communityId);
    ApiResponse<ProblemResponseDto.SubmitResponse> submitAnswer(Long problemId, ProblemRequestDto request);
    ApiResponse<List<ProblemResponseDto.WrongProblemResponse>> getWrongProblems();
}