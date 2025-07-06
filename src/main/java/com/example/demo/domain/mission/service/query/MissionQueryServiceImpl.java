package com.example.demo.domain.mission.service.query;


import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionResponseDto.MissionResponseDto;
import com.example.demo.global.dto.ApiResponse;
import com.example.demo.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MissionQueryServiceImpl implements MissionQueryService {
    private final UserUtil userUtil;

    @Override
    public ApiResponse<MissionResponseDto> getMissionPage(Long communityId) {
        return ApiResponse.success("미션 페이지 정보 조회 성공",
                MissionConverter.toMissionPageResponse("토익", 12, 2, 30));
    }
}
