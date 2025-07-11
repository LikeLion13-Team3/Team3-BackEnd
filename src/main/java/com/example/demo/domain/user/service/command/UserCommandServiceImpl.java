package com.example.demo.domain.user.service.command;

import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.user.service.command.UserCommandService;
import com.example.demo.global.apiPayload.code.BusinessException;
import com.example.demo.global.apiPayload.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional // 회원가입 트랜잭션 적용
    public void signup(UserRequestDto.Signup request) {
        // 1. 아이디 중복 검사
        if (userRepository.findByLoginId(request.getLoginId()).isPresent()) {
            // 이미 존재하는 아이디라면 BusinessException 발생 (ErrorCode.DUPLICATE_LOGIN_ID 사용)
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword()); // <<<--- 비밀번호 암호화

        // 3. User 엔티티 생성
        User user = User.builder()
                .loginId(request.getLoginId())
                .password(encodedPassword) // <<<--- 암호화된 비밀번호 사용
                .username(request.getUsername())
                .nickname(request.getNickname())
                .build();

        // user.updateProfile(request.getNickname(), null);
        // 이 줄은 User.builder()에서 nickname을 이미 설정하고, profileImageUrl은 null로 초기화되므로
        // 중복되거나 불필요할 수 있습니다. User 엔티티의 @Builder 생성자나 초기화 로직을 확인하여
        // 이 줄을 제거해도 되는지 판단하세요. (일반적으로 제거하는 것이 더 깔끔합니다.)

        // 4. DB 저장
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