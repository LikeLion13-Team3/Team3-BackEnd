package com.example.demo.domain.user.service.query;


import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.apiPayload.code.BusinessException;
import com.example.demo.global.apiPayload.code.ErrorCode;
import com.example.demo.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto.Login login(UserRequestDto.Login request) {
        // 1. loginId로 사용자 조회
        // User not found 시, IllegalArgumentException 대신 BusinessException을 던지는 것이 좋습니다.
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)); // 또는 ErrorCode.LOGIN_FAILED (아이디/비번 불일치 통합)

        // 2. 비밀번호 비교 (핵심 수정 부분)
        // 사용자가 입력한 평문 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교합니다.
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // 비밀번호 불일치 시 예외 발생
            throw new BusinessException(ErrorCode.INVALID_PASSWORD); // 또는 ErrorCode.LOGIN_FAILED (아이디/비번 불일치 통합)
        }

        // 3. 로그인 성공 시 JWT 토큰 생성
        String accessToken = jwtUtil.createAccessToken(user.getLoginId());
        String refreshToken = jwtUtil.createRefreshToken(user.getLoginId());

        // 4. 응답 DTO 반환
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