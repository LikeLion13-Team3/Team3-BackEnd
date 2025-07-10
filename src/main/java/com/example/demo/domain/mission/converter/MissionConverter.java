package com.example.demo.domain.mission.converter;

import com.example.demo.domain.mission.dto.MissionResponseDto.MissionResponseDto;

public class MissionConverter {
    public static MissionResponseDto toMissionPageResponse(String title, int solved, int rank, int total) {
        return MissionResponseDto.builder()
                .missionTitle(title)
                .solvedCount(solved)
                .ranking(rank)
                .totalUsers(total)
                .build();
    }
}
