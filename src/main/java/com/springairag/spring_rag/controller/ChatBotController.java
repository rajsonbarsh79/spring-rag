package com.springairag.spring_rag.controller;

import com.springairag.spring_rag.entity.ChatRequest;
import com.springairag.spring_rag.service.ChatBotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatBotController {

    private final ChatBotService chatBotService;

    @PostMapping(
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Map<String, Object>> chat(
            @Valid @RequestBody ChatRequest request) {

        String userId = request.getUserId().trim();
        String question = request.getQuestion().trim();

        String answer = chatBotService.ask(question, userId);

        List<String> sources = chatBotService.getSources(question);

        return ResponseEntity.ok(
                Map.of(
                        "userId", userId,
                        "answer", answer,
                        "sources", sources
                )
        );
    }
}