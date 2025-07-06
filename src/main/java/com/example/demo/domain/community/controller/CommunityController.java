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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/communities")
@Tag(name = "커뮤니티", description = "커뮤니티 관련 API")
public class CommunityController {
    private final CommunityCommandService commandService;
    private final CommunityQueryService queryService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "커뮤니티 참여", description = "커뮤니티에 참여합니다.")
    @PostMapping("/{examId}/join")
    public ResponseEntity<ApiResponse<CommunityResponseDto.CommunityJoinResponseDto>> joinCommunity(
            @RequestHeader("Authorization") String token,
            @PathVariable Long examId
    ) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        Long userCommunityId = commandService.joinCommunity(examId, loginId);
        CommunityResponseDto.CommunityJoinResponseDto response = new CommunityResponseDto.CommunityJoinResponseDto(userCommunityId);
        return ResponseEntity.ok(new ApiResponse<>("success", "커뮤니티에 참여하였습니다.", response));
    }

}