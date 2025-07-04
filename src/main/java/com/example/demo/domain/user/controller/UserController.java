package com.example.demo.domain.user.controller;


import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;
import com.example.demo.domain.user.service.command.UserCommandService;
import com.example.demo.domain.user.service.query.UserQueryService;
import com.example.demo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final JwtUtil jwtUtil;

    @Operation(description = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto.CommonResponse> signup(@RequestBody UserRequestDto.Signup request) {
        userCommandService.signup(request);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "회원가입이 완료되었습니다.", null));
    }
    @Operation(description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto.CommonResponse> login(@RequestBody UserRequestDto.Login request) {
        UserResponseDto.Login loginResult = userQueryService.login(request);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "로그인 성공", loginResult));
    }
    @Operation(description = "프로필 수정")
    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto.CommonResponse> updateProfile(
            @RequestHeader("Authorization") String token,
            @RequestBody UserRequestDto.UpdateProfile request) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        userCommandService.updateProfile(loginId, request);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "내 정보가 수정되었습니다.", null));
    }
    @Operation(description = "회원 정보 조회")
    @GetMapping("/{loginId}")
    public ResponseEntity<UserResponseDto.CommonResponse> getUserInfo(@PathVariable String loginId) {
        UserResponseDto.UserInfo info = userQueryService.getUserInfo(loginId);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "사용자 정보 조회 성공", List.of(info)));
    }
    @Operation(description = "회원 탈퇴")
    @DeleteMapping("/me")
    public ResponseEntity<UserResponseDto.CommonResponse> deleteUser(@RequestHeader(value = "Authorization", required = false) String token) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        userCommandService.deleteUser(loginId);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "회원 탈퇴 처리되었습니다.", List.of()));
    }
    @Operation(description = "내가 좋아요 누른 게시글 조회")
    @GetMapping("/me/liked-posts")
    public ResponseEntity<UserResponseDto.CommonResponse> getLikedPosts(@RequestHeader("Authorization") String token) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        List<UserResponseDto.LikedPost> likedPosts = userQueryService.getLikedPosts(loginId);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "내가 좋아요한 게시글 목록입니다.", likedPosts));
    }
}

