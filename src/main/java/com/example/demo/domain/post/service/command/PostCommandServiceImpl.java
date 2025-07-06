package com.example.demo.domain.post.service.command;

public class PostCommandServiceImpl implements PostCommandService {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final PostRepository postRepository;
    private final UserCommunityRepository userCommunityRepository;

    @Override
    public Post createPost(Long communityId, String loginId, PostRequestDto.PostCreateRequestDto request) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("커뮤니티 없음"));

        boolean isParticipant = userCommunityRepository.existsByUserAndCommunity(user, community);
        if (!isParticipant)
            throw new IllegalStateException("해당 커뮤니티에 참여하지 않았습니다.");

        Post post = Post.builder()
                .user(user)
                .community(community)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return postRepository.save(post);
    }

}
