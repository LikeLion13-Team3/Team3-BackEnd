package com.example.demo.domain.comment.service.command;

public class CommentCommandServiceImpl {
@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void createComment(Long postId, String loginId, CommentRequestDto.CommentCreateRequestDto requestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder()
                .content(requestDto.getContent())
                .post(post)
                .user(user)
                .build();

        commentRepository.save(comment);
    }
}
