package com.example.demo.domain.community.controller;

import com.example.demo.domain.community.converter.CommunityConverter;
import com.example.demo.domain.community.dto.ResponseDto.CommunityResponseDto;
import com.example.demo.domain.community.entity.UserCommunity;
import com.example.demo.domain.community.service.command.CommunityCommandService;
import com.example.demo.domain.community.service.query.CommunityQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/communities")
@Tag(name = "커뮤니티", description = "커뮤니티 관련 API")
public class CommunityController {

    private final CommunityCommandService commandService;
    private final CommunityQueryService queryService;

    @Operation(summary = "커뮤니티 참여", description = "커뮤니티에 참여합니다.")
    @PostMapping("/{examId}/join")
    public ResponseEntity<ApiResponse<CommunityResponseDto.CommunityJoinResponseDto>> joinCommunity(
            @PathVariable Long examId,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        Long userCommunityId = commandService.joinCommunity(examId, loginId);
        CommunityResponseDto.CommunityJoinResponseDto response = new CommunityResponseDto.CommunityJoinResponseDto(userCommunityId);
        return ResponseEntity.ok(new ApiResponse<>("success", "커뮤니티에 참여하였습니다.", response));
    }

    @Operation(summary = "내가 참여한 커뮤니티 목록 조회", description = "유저가 참여한 커뮤니티 목록 조회")
    @GetMapping("/users/me/communities")
    public ResponseEntity<ApiResponse<List<CommunityResponseDto.CommunitySimpleResponseDto>>> getMyCommunities(
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        List<UserCommunity> userCommunities = queryService.getMyCommunities(loginId);
        List<CommunityResponseDto.CommunitySimpleResponseDto> result = CommunityConverter.toDtoList(userCommunities);
        return ResponseEntity.ok(new ApiResponse<>("success", "참여한 커뮤니티 목록입니다.", result));
    }

    @Operation(summary = "커뮤니티 탈퇴 API", description = "커뮤니티에서 탈퇴합니다.")
    @DeleteMapping("/{communityId}/leave")
    public ResponseEntity<ApiResponse<String>> leaveCommunity(
            @PathVariable Long communityId,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        commandService.leaveCommunity(loginId, communityId);
        return ResponseEntity.ok(new ApiResponse<>("success", "커뮤니티에서 탈퇴했습니다.", null));
    }
}