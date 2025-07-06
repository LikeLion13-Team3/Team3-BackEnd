package com.example.demo.domain.comment.controller;

@RestController
@RequiredArgsConstructor
@Tag(name = "댓글", description = "댓글 관련 API")
public class CommentController {
    @Operation(summary = "댓글 작성 API", description = "게시글에 댓글을 작성합니다.")
    @PostMapping("/api/posts/{postId}/comments")
    public ApiResponse<Void> createComment(
            @RequestHeader("Authorization") String token,
            @PathVariable Long postId,
            @RequestBody CommentRequestDto.CommentCreateRequestDto requestDto
    ) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        commentCommandService.createComment(postId, loginId, requestDto);
        return ApiResponse.onSuccess("댓글 작성 완료", null);
}
