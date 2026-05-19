package com.itmaxsoftwaretechsolutions.ai_generator_poc.controller;

import java.util.Base64;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.content.*;
import org.springframework.http.*;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;

import com.itmaxsoftwaretechsolutions.ai_generator_poc.dto.TicketEventRequest;
import com.itmaxsoftwaretechsolutions.ai_generator_poc.dto.TriageResponse;

@RestController
@RequestMapping("/api")
public class AiTriageController {

    private final ChatClient chatClient;

    public AiTriageController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/triage")
    public TriageResponse triage(@RequestBody TicketEventRequest request) {

        String finalPrompt = """
            You are an ITSM AI Ticket Triage Assistant.

            Analyze the event and return ONLY valid JSON.

            Rules:
            - priority must be one of: LOW, MEDIUM, HIGH, CRITICAL
            - category must be one of:
              NETWORK, SECURITY, INFRASTRUCTURE, SURVEILLANCE, APPLICATION
            - impact must be one of:
              LOW, BUSINESS, CRITICAL
            - team must be one of:
              FIELD_SUPPORT, NOC, SECURITY_TEAM, APPLICATION_SUPPORT
            - summary must be concise and professional
            - Include all operational observations inside:
                - summary
                - recommendedAction
            - If visible, include contextual details naturally
                such as:
                - vehicle type
                - obstruction
                - crowding
                - plate number
                - safety concerns
                - any other relevant details
            - If the image is logically consistent with the event type, use it to enhance the summary and recommended action.
            - If the image content contradicts the event type, prioritize the image content for triage decisions and note the discrepancy in the summary.   
            - If the image content is inconclusive, rely on the textual event details but note the uncertainty in the summary.
            - If the image content contradicts the event type, lower the confidence score to reflect the inconsistency.
            - Check also the logic consistency of the event, for example if the event type is "Illegal Parking" but the vehicle is not near a road side, note this in the summary and lower the confidence score.
            - Write the summary and recommended action in point form, using concise language.

            Event Details:
            Event ID: %s
            Date & Time: %s
            Camera Name: %s
            Event Type: %s
            Status: %s
            Handling User: %s

            Return format:
            {
                "priority": "..",
                "category": "...",
                "impact": "...",
                "team": "...",
                "slaMinutes": ...,
                "confidenceScore": ...,
                "recommendedAction": "...",
                "summary": "..."
            }
            """
            .formatted(
                    request.getEventId(),
                    request.getDateTime(),
                    request.getCameraName(),
                    request.getEventType(),
                    request.getStatus(),
                    request.getHandlingUser()
            );

        byte[] imageBytes = Base64.getDecoder()
                .decode(request.getImageBase64());

        Media media = Media.builder()
                .data(imageBytes)
                .mimeType(MimeTypeUtils.parseMimeType(MediaType.IMAGE_JPEG_VALUE))
                .build();   

        UserMessage userMessage = UserMessage.builder()
        .text(finalPrompt)
        .media(media)
        .build();

        return chatClient.prompt()
            .messages(userMessage)
            .call()
            .entity(TriageResponse.class);
    }
}
