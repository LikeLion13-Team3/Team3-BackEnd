package com.example.demo.domain.problem.dto.openAi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRequest {
    private String model = "gpt-3.5-turbo";
    private List<ChatMessage> messages;
}
