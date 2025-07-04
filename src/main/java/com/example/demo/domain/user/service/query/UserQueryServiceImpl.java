package com.example.demo.domain.user.service.query;


import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto.Login login(UserRequestDto.Login request) {
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("ID 또는 비밀번호가 일치하지 않습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("ID 또는 비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createAccessToken(user.getLoginId());
        String refreshToken = jwtUtil.createRefreshToken(user.getLoginId());

        return new UserResponseDto.Login(accessToken, refreshToken, user.getNickname());
    }

    @Override
    public UserResponseDto.UserInfo getUserInfo(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        return new UserResponseDto.UserInfo(
                user.getLoginId(),
                user.getUsername(),
                user.getNickname(),
                Boolean.TRUE.equals(user.getIsDeleted()),
                user.getCreatedAt()
        );
    }

    @Override
    public List<UserResponseDto.LikedPost> getLikedPosts(String loginId) {
        // 좋아요한 게시글 조회 로직은 Post, PostLike, Community 도메인 구현 후 추가
        return List.of(); //
    }
}