package com.springairag.spring_rag.client;

import com.springairag.spring_rag.loader.PromptLoader;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ChatBotClient {

    private final ChatClient chatClient;
    private final PromptLoader promptLoader;

    public ChatBotClient(
            ChatClient chatClient,
            PromptLoader promptLoader) {
        this.chatClient = chatClient;
        this.promptLoader = promptLoader;
    }

    public String askLLM(
            String question,
            String context,
            String userId) {

        if (!StringUtils.hasText(question)) {
            throw new IllegalArgumentException("Question must not be empty");
        }

        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("User ID must not be empty");
        }

        String systemPrompt = promptLoader.load("templates/systemPrompt.st");

        String safeContext = StringUtils.hasText(context)
                ? context
                : "No relevant information found.";

        return chatClient.prompt()
                .system(system -> system
                        .text(systemPrompt)
                        .param("context", safeContext))
                .user(question)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID, userId))
                .call()
                .content();
    }
}