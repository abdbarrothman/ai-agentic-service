package com.itmaxsoftwaretechsolutions.ai_generator_poc.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmaxsoftwaretechsolutions.ai_generator_poc.dto.PromptRequest;

@RestController
@RequestMapping("/api")
public class EmailController {

    private final ChatClient chatClient;

    public EmailController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/generate-email")
    public String generateEmail(@RequestBody PromptRequest request) {

        String finalPrompt = """
                Write a professional email.

                User request:
                %s
                """.formatted(request.getPrompt());

        return chatClient.prompt()
                .user(finalPrompt)
                .call()
                .content();
    }
}
