package com.example.demo.global.util;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.exception.UsernameNotFoundException;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        System.out.println("authentication = " + authentication);
        if (authentication == null) {
            throw new UsernameNotFoundException("인증 정보가 없습니다.");
        }
        System.out.println("isAuthenticated: " + authentication.isAuthenticated());
        Object principal = authentication.getPrincipal();
        System.out.println("principal: " + principal);

        if (principal instanceof String loginId) {
            return userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        throw new UsernameNotFoundException("로그인한 사용자를 찾을 수 없습니다.");
    }




}
