package com.springairag.spring_rag.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "document_embeddings")
public class DocumentEmbedding {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_embed_seq_gen")
    @SequenceGenerator(
            name = "doc_embed_seq_gen",
            sequenceName = "document_embeddings_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "vector(1024)")
    private float[] embedding;

    // getters & setters
}