package com.example.demo.domain.problem.dto.ProblemResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ProblemResponseDto  {

    @Data
    @Builder
    @Schema(description = "랜덤으로 생성된 문제 응답 DTO")
    public static class ProblemResponse {
        @Schema(description = "문제 ID", example = "1")
        private Long problemId;

        @Schema(description = "문제 내용", example = "What is the best synonym for 'efficient'?")
        private String content;

        @Schema(description = "객관식 선택지 목록", example = "[\"diligent\", \"expensive\", \"productive\", \"friendly\"]")
        private List<String> options;
    }

    @Data
    @Builder
    @Schema(description = "문제 제출 후 응답 DTO")
    public static class SubmitResponse {
        @Schema(description = "정답 여부", example = "true")
        private boolean isCorrect;

        @Schema(description = "해설", example = "'productive'는 'efficient'의 가장 적절한 동의어입니다.")
        private String explanation;

        @Schema(description = "현재 점수", example = "3")
        private int currentScore;
    }

    @Data
    @Builder
    @Schema(description = "틀린 문제 정보 응답 DTO")
    public static class WrongProblemResponse {
        @Schema(description = "문제 ID", example = "5")
        private Long problemId;

        @Schema(description = "문제 내용", example = "What does 'ubiquitous' mean?")
        private String content;

        @Schema(description = "사용자가 선택한 답변", example = "rare")
        private String selectedAnswer;

        @Schema(description = "정답", example = "everywhere")
        private String correctAnswer;

        @Schema(description = "해설", example = "'ubiquitous'는 '어디에나 존재하는'이라는 뜻입니다.")
        private String explanation;
    }
}