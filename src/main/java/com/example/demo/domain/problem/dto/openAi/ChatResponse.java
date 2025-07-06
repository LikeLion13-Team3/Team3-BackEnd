package com.example.demo.domain.problem.dto.openAi;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatResponse {
    private List<Choice> choices;

    @Data
    public static class Choice {
        private ChatMessage message;
    }
}
