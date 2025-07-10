package com.example.demo.domain.problem.dto.ProblemRequestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "문제 제출 요청 DTO")
public class ProblemRequestDto {

    @Schema(description = "사용자가 선택한 정답", example = "productive")
    private String selectedAnswer;
}
