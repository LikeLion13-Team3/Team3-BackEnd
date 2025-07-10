package com.example.demo.global.security;

import com.example.demo.global.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails; // UserDetails import 추가
import org.springframework.security.core.userdetails.UserDetailsService; // UserDetailsService import 추가
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils; // StringUtils import 추가
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService; // <--- UserDetailsService 주입 추가

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = resolveToken(request); // 토큰 추출 로직을 별도 메서드로 분리하여 통일성 유지

        if (token != null && jwtUtil.validateToken(token)) {
            try {
                String loginId = jwtUtil.getLoginId(token);

                // --- 핵심 변경: UserDetailsService를 사용하여 UserDetails 로드 ---
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

                // UserDetails 객체를 principal로 사용하여 Authentication 객체 생성
                // UserDetails가 가진 실제 권한 정보를 사용합니다.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );


                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("[DEBUG] JWT Token Validated. Authentication set for: " + loginId); // 디버그 로그 추가
            } catch (Exception e) {
                // 토큰은 유효하지만 사용자 정보 로드 실패 등의 예외 처리
                System.out.println("[DEBUG] Error setting authentication: " + e.getMessage()); // 디버그 로그 추가
                SecurityContextHolder.clearContext(); // 문제 발생 시 컨텍스트 비우기
            }
        } else {
            System.out.println("[DEBUG] No valid JWT Token found or token is invalid for request: " + request.getRequestURI()); // 디버그 로그 추가
        }

        filterChain.doFilter(request, response);
    }

    // 토큰 추출 로직을 별도 메서드로 분리
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 제거
        }
        return null;
    }
}