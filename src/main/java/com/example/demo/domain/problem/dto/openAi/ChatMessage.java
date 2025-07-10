package com.example.demo.domain.problem.dto.openAi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String role;
    private String content;

    public static ChatMessage ofSystem(String content) {
        return new ChatMessage(Role.SYSTEM.getValue(), content);
    }

    public static ChatMessage ofUser(String content) {
        return new ChatMessage(Role.USER.getValue(), content);
    }

    public static ChatMessage ofAssistant(String content) {
        return new ChatMessage(Role.ASSISTANT.getValue(), content);
    }
}
