package com.springairag.spring_rag.service;

import com.springairag.spring_rag.client.ChatBotClient;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatBotService {

    private final VectorStore vectorStore;
    private final ChatBotClient chatBotClient;

    private static final int TOP_K = 5;
    private static final double SIMILARITY_THRESHOLD = 0.65;

    public String ask(String question, String userId) {

        SearchRequest request = SearchRequest.builder()
                .query(question)
                .topK(TOP_K)
                .similarityThreshold(SIMILARITY_THRESHOLD)
                .build();

        List<Document> docs = vectorStore.similaritySearch(request);

        String context = docs.stream().limit(3)
                .map(Document::getText)
                .limit(3)
                .collect(Collectors.joining("\n\n"));

        return chatBotClient.askLLM(question, context, userId);
    }

    public List<String> getSources(String question) {
        SearchRequest request = SearchRequest.builder().query(question).topK(TOP_K).similarityThreshold(SIMILARITY_THRESHOLD).build();
        List<Document> docs = vectorStore.similaritySearch(request);
        return docs.stream().limit(3).map(doc -> doc.getMetadata().get("source")).filter(source -> source != null).map(Object::toString).distinct().toList();
    }
}