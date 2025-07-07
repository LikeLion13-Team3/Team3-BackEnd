package com.example.demo.domain.community.converter;

import com.example.demo.domain.community.dto.ResponseDto.CommunityResponseDto;
import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.entity.UserCommunity;

import java.util.List;
import java.util.stream.Collectors;

public class CommunityConverter {

    public static List<CommunityResponseDto.CommunitySimpleResponseDto> toDtoList(List<UserCommunity> userCommunities) {
        return userCommunities.stream()
                .map(CommunityConverter::toDto)
                .collect(Collectors.toList());
    }

    public static CommunityResponseDto.CommunitySimpleResponseDto toDto(UserCommunity userCommunity) {
        Community community = userCommunity.getCommunity();
        return CommunityResponseDto.CommunitySimpleResponseDto.builder()
                .communityId(community.getId())
                .communityName(community.getCommunityName())
                .build();
    }
}
