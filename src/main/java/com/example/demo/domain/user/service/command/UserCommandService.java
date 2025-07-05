package com.example.demo.domain.user.service.command;

import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;

public interface UserCommandService {
    void signup(UserRequestDto.Signup request);
    void updateProfile(String loginId, UserRequestDto.UpdateProfile request);
    void deleteUser(String loginId);
}