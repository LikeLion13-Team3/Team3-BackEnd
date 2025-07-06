package com.example.demo.domain.post.controller;


public class PostController {
    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "게시글 작성 API", description = "커뮤니티에 게시글을 작성합니다.")
    @PostMapping("/{communityId}/posts")
    public ResponseEntity<ApiResponse<PostResponseDto.PostCreateResponseDto>> createPost(
            @RequestHeader("Authorization") String token,
            @PathVariable Long communityId,
            @RequestBody PostRequestDto.PostCreateRequestDto requestDto
    ) {
        String loginId = jwtUtil.getLoginId(token.replace("Bearer ", ""));
        Post post = postCommandService.createPost(communityId, loginId, requestDto);
        PostResponseDto.PostCreateResponseDto responseDto = PostConverter.toCreateResponseDto(post);

        return ResponseEntity.ok(new ApiResponse<>("success", "게시글이 작성되었습니다.", responseDto));
    }


}