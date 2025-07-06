package com.example.demo.domain.post.converter;

import com.example.demo.domain.post.dto.PostRequestDto.PostRequestDto;
import com.example.demo.domain.post.dto.PostResponseDto.PostResponseDto;
import com.example.demo.domain.post.entity.Post;

public class PostConverter {

    public static PostResponseDto.PostCreateResponseDto toCreateResponseDto(Post post) {
        return PostResponseDto.PostCreateResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getUsername())
                .build();
    }

    public static PostResponseDto.PostSummaryResponseDto toSummaryResponseDto(Post post) {
        return PostResponseDto.PostSummaryResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .writer(post.getUser().getUsername())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static void updatePost(Post post, PostRequestDto.PostUpdateRequestDto dto) {
        post.update(dto.getTitle(), dto.getContent());
    }
}
