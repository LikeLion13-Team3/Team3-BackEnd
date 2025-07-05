package com.example.demo.domain.user.service.command;

import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.user.service.command.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    @Override
    public void signup(UserRequestDto.Signup request) {
        User user = User.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .username(request.getUsername())
                .nickname(request.getNickname())
                .build();
        user.updateProfile(request.getNickname(), null);
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void updateProfile(String loginId, UserRequestDto.UpdateProfile request) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));
        user.updateProfile(request.getNickname(), request.getProfileImageUrl());
    }
    @Transactional
    @Override
    public void deleteUser(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));
        user.deleteUser();
    }
}