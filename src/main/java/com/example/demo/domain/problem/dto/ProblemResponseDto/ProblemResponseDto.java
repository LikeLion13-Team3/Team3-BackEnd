package com.example.demo.domain.problem.dto.ProblemResponseDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ProblemResponseDto  {

    @Data
    @Builder
    public static class ProblemResponse {
        private Long problemId;
        private String content;
        private List<String> options;
    }

    @Data
    @Builder
    public static class SubmitResponse {
        private boolean isCorrect;
        private String explanation;
        private int currentScore;
    }

    @Data
    @Builder
    public static class WrongProblemResponse {
        private Long problemId;
        private String content;
        private String selectedAnswer;
        private String correctAnswer;
        private String explanation;
    }
}