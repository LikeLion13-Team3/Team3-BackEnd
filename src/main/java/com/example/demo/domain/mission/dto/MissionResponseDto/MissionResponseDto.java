package com.example.demo.domain.mission.dto.MissionResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissionResponseDto {

    @Schema(description = "미션 풀이 페이지 이름", example = "토익 커뮤니티의 미션 페이지")
    private String missionTitle;

    @Schema(description = "해당 커뮤니티에서의 문제 풀이 수", example = "10")
    private int solvedCount;

    @Schema(description = "해당 커뮤니티에 속한 사함 들 중 순위", example = "1")
    private int ranking;

    @Schema(description = "해당 커뮤니티에 속한 유저 인원", example = "20")
    private int totalUsers;
}
