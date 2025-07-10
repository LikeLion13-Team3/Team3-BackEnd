package com.example.demo.domain.problem.controller;


import com.example.demo.domain.problem.dto.ProblemRequestDto.ProblemRequestDto;
import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import com.example.demo.domain.problem.service.command.ProblemCommandServiceImpl;
import com.example.demo.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "문제", description = "문제 관련 API")
public class ProblemController {

    private final ProblemCommandServiceImpl problemCommandService;

    @Operation(summary = "문제 랜덤 조회 API", description = "문제 랜덤 조회합니다.")
    @GetMapping("/communities/{communityId}/missions/problem")
    public ResponseEntity<ApiResponse<ProblemResponseDto.ProblemResponse>> getRandomProblem(@PathVariable Long communityId) {
        return ResponseEntity.ok(problemCommandService.getRandomProblem(communityId));
    }

    @Operation(summary = "문제 정답 제출 API", description = "문제 정답 제출합니다.")
    @PostMapping("/problem/{problemId}/submit")
    public ResponseEntity<ApiResponse<ProblemResponseDto.SubmitResponse>> submitAnswer(
            @PathVariable Long problemId, @RequestBody ProblemRequestDto request) {
        return ResponseEntity.ok(problemCommandService.submitAnswer(problemId, request));
    }

    @Operation(summary = "틀린 문제 조회 API", description = "틀린 문제 조회합니다.")
    @GetMapping("/users/me/missions/wrong-questions")
    public ResponseEntity<ApiResponse<List<ProblemResponseDto.WrongProblemResponse>>> getWrongProblems() {
        return ResponseEntity.ok(problemCommandService.getWrongProblems());
    }
}