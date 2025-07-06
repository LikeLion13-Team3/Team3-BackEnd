package com.example.demo.domain.mission.dto.MissionResponseDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MissionResponseDto {
    private String missionTitle;
    private int solvedCount;
    private int ranking;
    private int totalUsers;
}
