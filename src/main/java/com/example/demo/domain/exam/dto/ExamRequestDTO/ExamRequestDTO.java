package com.example.demo.domain.exam.dto.ExamRequestDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

public class ExamRequestDTO {

    // exam.sql로 시험 정보를 제공하기에 시험 생성을 누가 따로 하지 않아서 필요하지 않을 것으로 예상됨!
//    @Builder
//    public record ExamReqDTO(
//
//            @Schema(description = "시험 이름", example = "토익")
//            String name,
//
//            @Schema(description = "시험 설명", example = "영어 능력 평가 시험")
//            String description
//    ) {}
}

