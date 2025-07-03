package com.example.demo.domain.exam.dto.ExamResponseDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExamResponseDTO {

    @Schema(description = "시험 조회 응답 DTO")
    public record ExamResDTO(
            @Schema(description = "시험 ID", example = "1")
            Long id,

            @Schema(description = "시험 이름", example = "토익")
            String name,

            @Schema(description = "시험 설명", example = "국제 실용 영어 능력 시험")
            String description
    ) {
    }
}
