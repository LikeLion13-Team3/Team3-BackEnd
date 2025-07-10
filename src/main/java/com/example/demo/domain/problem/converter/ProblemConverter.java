package com.example.demo.domain.problem.converter;

import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import com.example.demo.domain.problem.dto.openAi.ProblemForm;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.UserProblem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ProblemConverter {

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
                .correctAnswer(up.getProblem().getCorrectAnswer())
                .explanation(up.getProblem().getSolution())
                .build();
    }

    public static ProblemResponseDto.ProblemResponse toProblemResponse(Problem problem) {
        ObjectMapper mapper = new ObjectMapper();
        List<String> options;
        try {
            options = mapper.readValue(problem.getOptions(), new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new RuntimeException("옵션 파싱 실패", e);
        }

        return ProblemResponseDto.ProblemResponse.builder()
                .problemId(problem.getId())
                .content(problem.getContent())
                .options(options)
                .build();
    }

}

