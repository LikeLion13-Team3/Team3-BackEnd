package com.example.demo.domain.post.service.command;

import com.example.demo.domain.post.dto.PostRequestDto.PostRequestDto;
import com.example.demo.domain.post.entity.Post;

public interface PostCommandService {
    Post createPost(Long communityId, String loginId, PostRequestDto.PostCreateRequestDto request);
    void updatePost(Long postId, String loginId, PostRequestDto.PostUpdateRequestDto dto);
    void deletePost(Long postId, String loginId);
}
