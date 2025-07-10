package com.example.demo.domain.problem.dto.openAi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ProblemForm {
    @Schema(description = "문제 내용", example = "컴퓨터의 중앙처리장치(CPU)가 수행하는 기본적인 작업이 아닌 것은?")
    private String content;

    @Schema(description = "객관식 보기", example = "[\"산술 연산 수행\", \"데이터 저장\", \"명령어 해석\", \"제어 신호 생성\"]")
    private List<String> options;

    @Schema(description = "정답", example = "데이터 저장")
    private String correctAnswer;

    @Schema(description = "문제 해설", example = "데이터 저장은 주로 메모리(RAM)나 하드디스크(HDD/SSD)의 역할이며, CPU는 주로 연산과 제어를 수행합니다.")
    private String explanation;
}
