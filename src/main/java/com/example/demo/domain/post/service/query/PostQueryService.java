package com.example.demo.domain.post.service.query;

import com.example.demo.domain.post.dto.PostResponseDto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryService {
    Page<PostResponseDto.PostSummaryResponseDto> getCommunityPosts(Long communityId, Pageable pageable);
    PostResponseDto.PostCreateResponseDto getCommunityPost(Long communityId, Long postId);
}
