package com.example.demo.domain.post.service.command;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.exception.CommunityErrorCode;
import com.example.demo.domain.community.repository.CommunityRepository;
import com.example.demo.domain.community.repository.UserCommunityRepository;
import com.example.demo.domain.post.converter.PostConverter;
import com.example.demo.domain.post.dto.PostRequestDto.PostRequestDto;
import com.example.demo.domain.post.entity.Post;
import com.example.demo.domain.post.exception.PostErrorCode;
import com.example.demo.domain.post.repository.PostRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.exception.UserErrorCode;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.apiPayload.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandServiceImpl implements PostCommandService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final PostRepository postRepository;
    private final UserCommunityRepository userCommunityRepository;

    @Override
    public Post createPost(Long communityId, String loginId, PostRequestDto.PostCreateRequestDto request) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(UserErrorCode.USER_NOT_FOUND));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new CustomException(CommunityErrorCode.COMMUNITY_NOT_FOUND));

        boolean isParticipant = userCommunityRepository.existsByUserAndCommunity(user, community);
        if (!isParticipant)
            throw new CustomException(CommunityErrorCode.NOT_PARTICIPATED);

        Post post = Post.builder()
                .user(user)
                .community(community)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return postRepository.save(post);
    }

    @Override
    public void updatePost(Long postId, String loginId, PostRequestDto.PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getLoginId().equals(loginId)) {
            throw new CustomException(PostErrorCode.POST_FORBIDDEN);
        }

        post.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Override
    public void deletePost(Long postId, String loginId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(PostErrorCode.POST_NOT_FOUND));

        if (!post.getUser().getLoginId().equals(loginId)) {
            throw new CustomException(PostErrorCode.POST_FORBIDDEN);
        }

        postRepository.delete(post);
    }
}
