package com.example.demo.domain.user.dto.UserRequestDto;


import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class Signup {
        private String loginId;
        private String password;
        private String username;
        private String nickname;
    }

    @Getter
    public static class Login {
        private String loginId;
        private String password;
    }

    @Getter
    public static class UpdateProfile {
        private String nickname;
        private String currentPassword;
        private String newPassword;
    }
}

