package com.example.demo.domain.problem.converter;

import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.UserProblem;

import java.util.List;

public class ProblemConverter {
    public static ProblemResponseDto.ProblemResponse toProblemResponse(Problem problem) {
        return ProblemResponseDto.ProblemResponse.builder()
                .problemId(problem.getId())
                .content(problem.getContent())
                .options(List.of("Round Robin", "SJF", "MLFQ", "FCFS")) // 예시 데이터
                .build();
    }

    public static ProblemResponseDto.SubmitResponse toSubmitResponse(boolean isCorrect, String solution, int score) {
        return ProblemResponseDto.SubmitResponse.builder()
                .isCorrect(isCorrect)
                .explanation(solution)
                .currentScore(score)
                .build();
    }

    public static ProblemResponseDto.WrongProblemResponse toWrongProblemResponse(UserProblem up) {
        return ProblemResponseDto.WrongProblemResponse.builder()
                .problemId(up.getProblem().getId())
                .content(up.getProblem().getContent())
                .selectedAnswer(up.getSubmittedAnswer())
                .correctAnswer("FCFS") // 예시 정답
                .explanation(up.getProblem().getSolution())
                .build();
    }
}

