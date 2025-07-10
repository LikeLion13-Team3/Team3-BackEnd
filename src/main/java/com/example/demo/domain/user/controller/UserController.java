package com.example.demo.domain.user.controller;


import com.example.demo.domain.user.dto.UserRequestDto.UserRequestDto;
import com.example.demo.domain.user.dto.UserResponseDto.UserResponseDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.service.command.UserCommandService;
import com.example.demo.domain.user.service.query.UserQueryService;
import com.example.demo.global.util.JwtUtil;
import com.example.demo.global.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 관련 API")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final JwtUtil jwtUtil;
    private final UserUtil userUtil;

    @Operation(summary = "회원가입", description = "유저가 회원가입을 수행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto.CommonResponse> signup(@RequestBody UserRequestDto.Signup request) {
        userCommandService.signup(request);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "회원가입이 완료되었습니다.", null));
    }

    @Operation(summary = "로그인", description = "유저가 로그인을 수행합니다.")
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto.CommonResponse> login(@RequestBody UserRequestDto.Login request) {
        UserResponseDto.Login loginResult = userQueryService.login(request);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "로그인 성공", loginResult));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto.CommonResponse> updateProfile(
            @RequestBody UserRequestDto.UpdateProfile request) {
        User currentUser = userUtil.getLoginUser(); // UserUtil을 통해 현재 로그인한 User 엔티티 객체를 가져옵니다.
        String loginId = currentUser.getLoginId();
        //String loginId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userCommandService.updateProfile(loginId, request);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "내 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "회원 정보 조회", description = "회원 정보 조회를 수행합니다.")
    @GetMapping("/{loginId}")
    public ResponseEntity<UserResponseDto.CommonResponse> getUserInfo(@PathVariable String loginId) {
        UserResponseDto.UserInfo info = userQueryService.getUserInfo(loginId);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "사용자 정보 조회 성공", List.of(info)));
    }

    @Operation(summary = "회원 탈퇴", description = "유저가 회원 탈퇴를 수행합니다.")
    @DeleteMapping("/me")
    public ResponseEntity<UserResponseDto.CommonResponse> deleteUser(@RequestHeader(value = "Authorization", required = false) String token) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        userCommandService.deleteUser(loginId);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "회원 탈퇴 처리되었습니다.", List.of()));
    }

    @Operation(summary = "좋아요 누른 게시글 조회", description = "유저가 좋아요 누른 게시글 조회를 수행합니다.")
    @GetMapping("/me/liked-posts")
    public ResponseEntity<UserResponseDto.CommonResponse> getLikedPosts(@RequestHeader("Authorization") String token) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        List<UserResponseDto.LikedPost> likedPosts = userQueryService.getLikedPosts(loginId);
        return ResponseEntity.ok(new UserResponseDto.CommonResponse("success", "내가 좋아요한 게시글 목록입니다.", likedPosts));
    }
}

