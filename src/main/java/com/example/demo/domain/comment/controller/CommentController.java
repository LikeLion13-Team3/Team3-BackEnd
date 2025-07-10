package com.example.demo.domain.comment.controller;

import com.example.demo.domain.comment.dto.CommentRequestDto.CommentRequestDto;
import com.example.demo.domain.comment.dto.CommentResponseDto.CommentResponseDto;
import com.example.demo.domain.comment.service.command.CommentCommandService;
import com.example.demo.domain.comment.service.query.CommentQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글", description = "댓글 관련 API")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(summary = "댓글 작성 API", description = "게시글에 댓글을 작성합니다.")
    @PostMapping("/api/posts/{postId}/comments")
    public ApiResponse<Void> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto.CommentCreateRequestDto requestDto,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        commentCommandService.createComment(postId, loginId, requestDto);
        return ApiResponse.onSuccess("댓글 작성 완료", null);
    }

    @Operation(summary = "댓글 수정 API", description = "댓글을 수정합니다.")
    @PatchMapping("/api/comments/{commentId}")
    public ApiResponse<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto.CommentUpdateRequestDto dto,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        commentCommandService.updateComment(commentId, loginId, dto);
        return ApiResponse.onSuccess("댓글이 수정되었습니다", null);
    }

    @Operation(summary = "댓글 목록 조회 API", description = "댓글 목록을 조회합니다.")
    @GetMapping("/api/posts/{postId}/comments")
    public ApiResponse<List<CommentResponseDto.CommentSimpleResponseDto>> getCommentsByPostId(
            @PathVariable Long postId
    ) {
        return ApiResponse.onSuccess("댓글 목록입니다.", commentQueryService.getCommentsByPostId(postId));
    }

    @Operation(summary = "댓글 삭제 API", description = "댓글을 삭제합니다.")
    @DeleteMapping("/api/comments/{commentId}")
    public ApiResponse<Void> deleteComment(
            @PathVariable Long commentId,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        commentCommandService.deleteComment(commentId, loginId);
        return ApiResponse.onSuccess("댓글 삭제 완료", null);
    }
}