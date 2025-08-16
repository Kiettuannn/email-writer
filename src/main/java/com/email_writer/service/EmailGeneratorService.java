package com.email_writer.service;

import com.email_writer.dto.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmailGeneratorService {

    private final ChatClient chatClient;

    public EmailGeneratorService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    public String generateEmailReply(EmailRequest emailRequest) {
        ChatOptions chatOptions = ChatOptions.builder()
                .temperature(0D)
                .build();

        SystemMessage systemMessage = new SystemMessage(buildSystemPrompt(emailRequest.getTone()));
        UserMessage userMessage = new UserMessage(emailRequest.getEmailContent());

        Prompt prompt = new Prompt(systemMessage, userMessage);


        return chatClient
                .prompt(prompt)
                .options(chatOptions)
                .call()
                .content();
    }


    private String buildSystemPrompt(String tone) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line");
        if(tone != null && !tone.isEmpty()) {
            prompt.append("Use a ").append(tone).append(" tone.");
        }
        return prompt.toString();
    }
}
