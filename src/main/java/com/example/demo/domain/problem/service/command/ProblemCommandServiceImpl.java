package com.example.demo.domain.problem.service.command;

import com.example.demo.domain.community.entity.Community;
import com.example.demo.domain.community.repository.CommunityRepository;
import com.example.demo.domain.problem.converter.ProblemConverter;
import com.example.demo.domain.problem.dto.ProblemRequestDto.ProblemRequestDto;
import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import com.example.demo.domain.problem.dto.openAi.ChatMessage;
import com.example.demo.domain.problem.dto.openAi.ChatRequest;
import com.example.demo.domain.problem.dto.openAi.ChatResponse;
import com.example.demo.domain.problem.dto.openAi.ProblemForm;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.UserProblem;
import com.example.demo.domain.problem.repository.ProblemRepository;
import com.example.demo.domain.problem.repository.UserProblemRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.UserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemCommandServiceImpl implements ProblemCommandService {

    @Value("${openai.api.key}")
    private String openAiKey;

    private final CommunityRepository communityRepository;
    private final ProblemRepository problemRepository;
    private final UserUtil userUtil;
    private final WebClient webClient;
    private final UserProblemRepository userProblemRepository;

    @Override
    public ApiResponse<List<ProblemResponseDto.WrongProblemResponse>> getWrongProblems() {
        User user = userUtil.getLoginUser();

        List<UserProblem> wrongProblems = userProblemRepository.findWrongProblemsByUser(user);

        List<ProblemResponseDto.WrongProblemResponse> responseList = wrongProblems.stream()
                .map(ProblemConverter::toWrongProblemResponse)
                .toList();

        return ApiResponse.onSuccess("틀린 문제 목록입니다.", responseList);
    }

    @Override
    public ApiResponse<ProblemResponseDto.ProblemResponse> getRandomProblem(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커뮤니티가 존재하지 않습니다."));
        User user = userUtil.getLoginUser();

        List<Problem> allProblems = problemRepository.findByMission_CommunityId(communityId);

        List<UserProblem> solvedProblems = userProblemRepository.findByUser(user);
        Set<Long> solvedProblemIds = solvedProblems.stream()
                .map(up -> up.getProblem().getId())
                .collect(Collectors.toSet());

        List<Problem> unsolvedProblems = allProblems.stream()
                .filter(p -> !solvedProblemIds.contains(p.getId()))
                .toList();

        if (!unsolvedProblems.isEmpty()) {
            Problem randomProblem = unsolvedProblems.get(new Random().nextInt(unsolvedProblems.size()));
            return ApiResponse.onSuccess("문제 풀이 시작", ProblemConverter.toProblemResponse(randomProblem));
        }

        String examName = community.getExam().getExamName();

        ChatMessage systemMsg = new ChatMessage("system", "당신은 자격증 시험 문제를 생성하는 AI 도우미입니다.");
        ChatMessage userMsg = new ChatMessage("user",
                "다음은 " + examName + " 자격증 시험을 위한 문제를 생성하는 요청입니다. " +
                        examName + "의 실제 기출 문제 스타일을 모방해서, 실제 시험에서 나올 법한 4지선다형 문제를 하나 만들어주세요. " +
                        " 어학 시험은 해당 시험의 언어로 문제와 보기, 해답을 제공하고 나머지 시험은 한글로 해석해서 문제와 보기, 해답을 제공해주세요. " +
                        "JSON 형식으로 출력해 주세요. 다음 형식을 지켜 주세요: " +
                        "{\"content\": \"문제 내용\", \"options\": [\"보기1\", \"보기2\", \"보기3\", \"보기4\"], \"correctAnswer\": \"정답\", \"explanation\": \"해설\"}");

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setModel("gpt-3.5-turbo");
        chatRequest.setMessages(List.of(systemMsg, userMsg));

        ChatResponse chatResponse = webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openAiKey)
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        if (chatResponse == null || chatResponse.getChoices().isEmpty()) {
            throw new RuntimeException("ChatGPT API 응답 없음");
        }

        String content = chatResponse.getChoices().get(0).getMessage().getContent();
        ProblemForm form = parseProblemResponseFromJson(content);

        ObjectMapper mapper = new ObjectMapper();
        String optionsJson;
        try {
            optionsJson = mapper.writeValueAsString(form.getOptions());
        } catch (Exception e) {
            throw new RuntimeException("옵션 직렬화 실패", e);
        }

        Problem problem = Problem.builder()
                .content(form.getContent())
                .solution(form.getExplanation())
                .correctAnswer(form.getCorrectAnswer())
                .options(optionsJson)
                .mission(community.getMission())
                .build();

        problemRepository.save(problem);

        return ApiResponse.onSuccess("문제 풀이 시작", ProblemConverter.toProblemResponse(problem));
    }

    @Override
    public ApiResponse<ProblemResponseDto.SubmitResponse> submitAnswer(Long problemId, ProblemRequestDto request) {
        User user = userUtil.getLoginUser();
        Problem problem = problemRepository.findById(problemId).orElseThrow(() ->
                new RuntimeException("문제를 찾을 수 없습니다."));

        boolean isCorrect = problem.getCorrectAnswer().equals(request.getSelectedAnswer());
        int score = isCorrect ? 1 : 0;

        UserProblem userProblem = new UserProblem();
        userProblem.setUser(user);
        userProblem.setProblem(problem);
        userProblem.setSubmittedAnswer(request.getSelectedAnswer());
        userProblem.setCorrect(isCorrect);

        problem.getUserProblems().add(userProblem);
        problemRepository.save(problem);

        return isCorrect ?
                ApiResponse.onFailure("정답입니다!", ProblemConverter.toSubmitResponse(true, problem.getSolution(), score)) :
                ApiResponse.onFailure("오답입니다", ProblemConverter.toSubmitResponse(false, problem.getSolution(), score));
    }

    private ProblemForm parseProblemResponseFromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, ProblemForm.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }
}