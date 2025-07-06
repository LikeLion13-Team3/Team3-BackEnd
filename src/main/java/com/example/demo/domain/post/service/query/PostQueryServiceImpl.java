package com.example.demo.domain.post.service.query;


import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.repository.CommunityRepository;
import com.example.demo.domain.post.converter.PostConverter;
import com.example.demo.domain.post.dto.PostResponseDto.PostResponseDto;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {
    private final CommunityRepository communityRepository;
    private final PostRepository postRepository;

    @Override
    public Page<PostResponseDto.PostSummaryResponseDto> getCommunityPosts(Long communityId, Pageable pageable) {
        Page<Post> postPage = postRepository.findByCommunityId(communityId, pageable);

        return postPage.map(PostConverter::toSummaryResponseDto);
    }




}