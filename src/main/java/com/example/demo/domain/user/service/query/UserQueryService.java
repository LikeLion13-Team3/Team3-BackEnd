package com.example.demo.domain.user.service.query;

import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;

import java.util.List;

public interface UserQueryService {
    UserResponseDto.Login login(UserRequestDto.Login request);
    UserResponseDto.UserInfo getUserInfo(String loginId);
    List<UserResponseDto.LikedPost> getLikedPosts(String loginId);
}
