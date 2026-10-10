package com.springairag.spring_rag.service;

import com.springairag.spring_rag.client.EmbeddingClient;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final EmbeddingClient embeddingClient;

    public EmbeddingService(EmbeddingClient embeddingClient) {
        this.embeddingClient = embeddingClient;
    }

    public float[] generateEmbedding(String text) {
        return embeddingClient.embed(text);
    }
}