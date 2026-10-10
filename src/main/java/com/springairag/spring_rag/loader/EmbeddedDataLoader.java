package com.springairag.spring_rag.loader;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class EmbeddedDataLoader {

    private final VectorStore vectorStore;
    private final JdbcTemplate jdbcTemplate;

    public EmbeddedDataLoader(VectorStore vectorStore, JdbcTemplate jdbcTemplate) {
        this.vectorStore = vectorStore;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Async("aiTaskExecutor")
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        jdbcTemplate.execute("TRUNCATE TABLE spring_rag.vector_store");
        List<Document> documents = getDocuments().stream()
                .map(text -> new Document(
                        text,
                        Map.of("source", "company-policy")
                ))
                .toList();

        // 🔥 This handles:
        // embedding + storing + indexing
        vectorStore.add(documents);
        System.out.println("Successfully loaded " + documents.size() + " documents into the vector store.");
    }

    private List<String> getDocuments() {
        return List.of(
                "Employees must adhere to the company code of conduct at all times.",
                "Working hours are from 9 AM to 6 PM, Monday to Friday.",
                "All employees are entitled to 20 days of paid leave annually.",
                "Sick leave must be reported to the manager within 24 hours.",
                "Employees must maintain confidentiality of company data.",
                "Use of company assets should be strictly for official purposes.",
                "Harassment of any kind will not be tolerated in the workplace.",
                "Performance reviews will be conducted twice a year.",
                "Employees are encouraged to upskill through training programs.",
                "Remote work is allowed based on manager approval.",
                "All expenses must be submitted with valid receipts.",
                "Employees must comply with all security protocols.",
                "Unauthorized access to systems is strictly prohibited.",
                "Dress code should be maintained as per company standards.",
                "Late arrivals beyond 3 instances may lead to warnings.",
                "Resignation requires a minimum notice period of 30 days.",
                "Company reserves the right to terminate employment for misconduct.",
                "All employees must complete mandatory compliance training.",
                "Data privacy policies must be followed strictly.",
                "Use of personal devices for office work must be approved.",
                "Employees should avoid conflict of interest situations.",
                "Team collaboration and communication are highly encouraged.",
                "Overtime must be pre-approved by management.",
                "Employees must update their daily work status regularly."
        );
    }
}
