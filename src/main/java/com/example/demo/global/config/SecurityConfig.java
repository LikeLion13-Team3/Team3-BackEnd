package com.example.demo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (Swagger 편의)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/api/users/login",
                                "/api/users/{loginId}",
                                "/api/users/signup",
                                "/api/users/me",
                                "/api/notifications/**",
                                "/api/users/me/liked-posts",
                                "/api/communities/{communityId}/missions",
                                "/api/communities/{communityId}/missions/problem",
                                "/api/problem/{problemId}/submit",
                                "/api/users/me/missions/wrong-questions",
                                "/api/categories",
                                "/api/communities/{examId}/join",
                                "/api/communities/users/me/communities",
                                "/api/communities/{communityId}/leave",
                                "/api/categories/{categoryId}/exams"






                                ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())  // 기본 로그인 폼 제거
                .httpBasic(httpBasic -> httpBasic.disable()); // 기본 인증 제거

        return http.build();

    }
}

