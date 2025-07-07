package com.example.demo.domain.mission.service.query;

import com.example.demo.domain.mission.dto.MissionResponseDto.MissionResponseDto;
import com.example.demo.global.apiPayload.ApiResponse;

public interface MissionQueryService {
    ApiResponse<MissionResponseDto> getMissionPage(Long communityId);
}
