package com.example.demo.domain.post.service.query;

import com.example.demo.domain.post.dto.PostResponseDto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostQueryService {
    Page<PostResponseDto.PostSummaryResponseDto> getCommunityPosts(Long communityId, Pageable pageable);
}
