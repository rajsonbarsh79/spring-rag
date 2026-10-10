package com.springairag.spring_rag.repository;

import com.springairag.spring_rag.entity.DocumentEmbedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEmbedding, Long> {

    @Query(value = """
        SELECT id, content, embedding,
               (1 - (embedding <=> CAST(:embedding AS vector))) AS score
        FROM document_embeddings
        ORDER BY embedding <=> CAST(:embedding AS vector)
        LIMIT :limit
        """, nativeQuery = true)
    List<Object[]> findTopMatchesWithScore(@Param("embedding") String embedding,
                                           @Param("limit") int limit);
}