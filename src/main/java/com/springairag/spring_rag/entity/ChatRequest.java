package com.springairag.spring_rag.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatRequest {

    @NotBlank(message = "userId is required")
    private String userId;

    @NotBlank(message = "question is required")
    private String question;
}