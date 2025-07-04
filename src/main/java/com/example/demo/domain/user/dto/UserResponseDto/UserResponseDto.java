package com.example.demo.domain.user.dto.UserResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserResponseDto {

    @Getter
    @AllArgsConstructor
    public static class Login {
        private String accessToken;
        private String refreshToken;
        private String nickname;
    }

    @Getter
    @AllArgsConstructor
    public static class UserInfo {
        private String loginId;
        private String username;
        private String nickname;
        private boolean isDeleted;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    public static class LikedPost {
        private Long postId;
        private String title;
        private String content;
        private String imageUrl;
        private LocalDateTime likedAt;
        private String communityName;
    }

    @Getter
    @AllArgsConstructor
    public static class CommonResponse {
        private String status;
        private String message;
        private Object data;
    }
}

