package com.example.demo.domain.mission.service.query;


import com.example.demo.domain.community.repository.UserCommunityRepository;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionResponseDto.MissionResponseDto;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.problem.repository.UserProblemRepository;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final UserProblemRepository userProblemRepository;
    private final UserCommunityRepository userCommunityRepository;
    private final UserUtil userUtil;

    @Override
    public ApiResponse<MissionResponseDto> getMissionPage(Long communityId) {
        var user = userUtil.getLoginUser(); // 현재 로그인한 사용자

        boolean isJoined = userCommunityRepository.existsByUserIdAndCommunityId(user.getId(), communityId);
        if (!isJoined) {
            throw new IllegalArgumentException("해당 커뮤니티에 가입되어 있지 않습니다.");
        }

        var mission = missionRepository.findByCommunityId(communityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커뮤니티에 미션이 없습니다."));

        String missionTitle = mission.getTitle();
        int solvedCount = userProblemRepository.countByUserIdAndCommunityId(user.getId(), communityId);
        int ranking = userProblemRepository.getUserRankingInCommunity(user.getId(), communityId);
        int totalUsers = userCommunityRepository.countByCommunityId(communityId);

        var response = MissionConverter.toMissionPageResponse(missionTitle, solvedCount, ranking, totalUsers);
        return ApiResponse.onSuccess("미션 페이지 정보 조회 성공", response);
    }
}
