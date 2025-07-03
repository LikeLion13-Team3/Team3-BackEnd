package com.example.demo.domain.category.dto.ExamResponseDTO;

import com.example.demo.domain.category.entity.ExamCategory;
import io.swagger.v3.oas.annotations.media.Schema;

public class CategoryResponseDTO {

    @Schema(description = "카테고리 응답 DTO")
    public record CategoryResDTO(
            @Schema(description = "카테고리 ID", example = "1")
            Long id,

            @Schema(description = "카테고리 이름 (Enum)", example = "IT")
            ExamCategory categoryType
    ) {
    }
}
