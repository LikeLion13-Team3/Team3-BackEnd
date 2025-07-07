package com.example.demo.domain.post.controller;


import com.example.demo.domain.post.converter.PostConverter;
import com.example.demo.domain.post.dto.PostRequestDto.PostRequestDto;
import com.example.demo.domain.post.dto.PostResponseDto.PostResponseDto;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.service.command.PostCommandService;
import com.example.demo.domain.post.service.query.PostQueryService;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/communities")
@Tag(name = "게시글", description = "게시글 관련 API")
public class PostController {
    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    @Operation(summary = "게시글 작성 API", description = "커뮤니티에 게시글을 작성합니다.")
    @PostMapping("/{communityId}/posts")
    public ResponseEntity<ApiResponse<PostResponseDto.PostCreateResponseDto>> createPost(
            @PathVariable Long communityId,
            @RequestBody PostRequestDto.PostCreateRequestDto requestDto,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        Post post = postCommandService.createPost(communityId, loginId, requestDto);
        PostResponseDto.PostCreateResponseDto responseDto = PostConverter.toCreateResponseDto(post);
        return ResponseEntity.ok(new ApiResponse<>("success", "게시글이 작성되었습니다.", responseDto));
    }

    @Operation(summary = "게시글 목록 조회 API", description = "커뮤니티에 게시글 목록을 조회합니다.")
    @GetMapping("/{communityId}/posts")
    public ResponseEntity<ApiResponse<Page<PostResponseDto.PostSummaryResponseDto>>> getCommunityPosts(
            @PathVariable Long communityId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<PostResponseDto.PostSummaryResponseDto> page = postQueryService.getCommunityPosts(communityId, pageable);
        return ResponseEntity.ok(new ApiResponse<>("success", "게시글 목록입니다.", page));
    }

    @PatchMapping("/posts/{postId}")
    @Operation(summary = "게시글 수정 API", description = "게시글을 수정합니다.")
    public ResponseEntity<ApiResponse<String>> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto.PostUpdateRequestDto requestDto,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        postCommandService.updatePost(postId, loginId, requestDto);
        return ResponseEntity.ok(new ApiResponse<>("success", "게시글이 수정되었습니다.", null));
    }

    @DeleteMapping("/posts/{postId}")
    @Operation(summary = "게시글 삭제 API", description = "게시글을 삭제합니다.")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long postId,
            Authentication authentication
    ) {
        String loginId = (String) authentication.getPrincipal();
        postCommandService.deletePost(postId, loginId);
        return ResponseEntity.ok(new ApiResponse<>("success", "게시글 삭제 완료", null));
    }
}
