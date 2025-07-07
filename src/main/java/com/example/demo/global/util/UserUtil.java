package com.example.demo.global.util;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.exception.UsernameNotFoundException;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User getUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        // principal이 loginId 문자열일 수도 있음
        if (principal instanceof String loginId) {
            return userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }

        throw new UsernameNotFoundException("로그인한 사용자를 찾을 수 없습니다.");
    }


}
