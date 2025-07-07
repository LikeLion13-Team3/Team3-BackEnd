package com.example.demo.domain.problem.service.command;

import com.example.demo.domain.problem.converter.ProblemConverter;
import com.example.demo.domain.problem.dto.ProblemRequestDto.ProblemRequestDto;
import com.example.demo.domain.problem.dto.ProblemResponseDto.ProblemResponseDto;
import com.example.demo.domain.problem.dto.openAi.ChatMessage;
import com.example.demo.domain.problem.dto.openAi.ChatRequest;
import com.example.demo.domain.problem.dto.openAi.ChatResponse;
import com.example.demo.domain.problem.entity.Problem;
import com.example.demo.domain.problem.entity.UserProblem;
import com.example.demo.domain.problem.repository.ProblemRepository;
import com.example.demo.domain.problem.repository.UserProblemRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.util.UserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProblemCommandServiceImpl implements ProblemCommandService {

    private final ProblemRepository problemRepository;
    private final UserUtil userUtil;
    private final WebClient webClient;

    private final UserProblemRepository userProblemRepository;


    @Override
    public ApiResponse<List<ProblemResponseDto.WrongProblemResponse>> getWrongProblems() {
        User user = userUtil.getLoginUser();

        if (user == null) {
            System.out.println("[DEBUG] 로그인된 사용자 없음 - user is null");
            throw new RuntimeException("로그인된 사용자가 없습니다.");
        }

        System.out.println("[DEBUG] 로그인된 사용자 ID: " + user.getId());
        System.out.println("[DEBUG] 로그인된 사용자 이름: " + user.getUsername());


        List<UserProblem> wrongProblems = userProblemRepository.findWrongProblemsByUser(user);

        List<ProblemResponseDto.WrongProblemResponse> responseList = wrongProblems.stream()
                .map(ProblemConverter::toWrongProblemResponse)
                .toList();        // ...

        return ApiResponse.onSuccess("틀린 문제 목록입니다.", responseList);
    }


    @Override
    public ApiResponse<ProblemResponseDto.ProblemResponse> getRandomProblem(Long communityId) {
        // ChatGPT에게 요청할 메시지
        ChatMessage systemMsg = new ChatMessage("system", "You are a helpful assistant.");
        ChatMessage userMsg = new ChatMessage("user",
                "토익 기출 문제 하나를 4지선다형으로 JSON 형식(문제, 옵션 리스트, 정답, 해설 포함)으로 만들어줘.");

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setMessages(List.of(systemMsg, userMsg));

        ChatResponse chatResponse = webClient.post()
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .block();

        if (chatResponse == null || chatResponse.getChoices().isEmpty()) {
            throw new RuntimeException("ChatGPT API 응답 없음");
        }

        String content = chatResponse.getChoices().get(0).getMessage().getContent();

        // content는 JSON 텍스트로 가정. 직접 파싱 필요
        ProblemResponseDto.ProblemResponse problemResponse = parseProblemResponseFromJson(content);

        return ApiResponse.onSuccess("문제 풀이 시작", problemResponse);
    }


    @Override
    public ApiResponse<ProblemResponseDto.SubmitResponse> submitAnswer(Long problemId, ProblemRequestDto request) {
        User user = userUtil.getLoginUser();
        Problem problem = problemRepository.findById(problemId).orElseThrow(() ->
                new RuntimeException("문제를 찾을 수 없습니다."));

        boolean isCorrect = "FCFS".equals(request.getSelectedAnswer()); // 임시 정답 체크
        int score = isCorrect ? 1 : 0;

        // UserProblem 엔티티 생성 후 저장
        UserProblem userProblem = new UserProblem();
        userProblem.setUser(user);
        userProblem.setProblem(problem);
        userProblem.setSubmittedAnswer(request.getSelectedAnswer());
        userProblem.setCorrect(isCorrect);

        // 문제 엔티티에 userProblem 연결
        problem.getUserProblems().add(userProblem);
        problemRepository.save(problem); // cascade 설정이 되어 있다면 userProblem도 함께 저장됨

        return isCorrect ?
                ApiResponse.onFailure("정답입니다!", ProblemConverter.toSubmitResponse(true, problem.getSolution(), score)) :
                ApiResponse.onFailure("오답입니다", ProblemConverter.toSubmitResponse(false, problem.getSolution(), score));
    }



    private ProblemResponseDto.ProblemResponse parseProblemResponseFromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, ProblemResponseDto.ProblemResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }
}
