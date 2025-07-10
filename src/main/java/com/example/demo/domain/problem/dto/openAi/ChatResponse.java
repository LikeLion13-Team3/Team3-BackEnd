package com.example.demo.domain.problem.dto.openAi;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatResponse {
    private List<Choice> choices;
    private String id;
    private String model;
    private long created;
    private Usage usage;

    @Data
    public static class Choice {
        private ChatMessage message;
        private String finish_reason;
        private int index;
    }

    @Data
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}
