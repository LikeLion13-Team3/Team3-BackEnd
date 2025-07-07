package com.example.demo.domain.community.dto.ResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommunityResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommunityJoinResponseDto {
        @Schema(description = "userCommunity의 pk", example = "1")
        private Long userCommunityId;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommunitySimpleResponseDto {


        @Schema(description = "커뮤니티 ID", example = "1")
        private Long communityId;

        @Schema(description = "커뮤니티 이름", example = "토익 시험 준비 커뮤니티")
        private String communityName;
    }

}
