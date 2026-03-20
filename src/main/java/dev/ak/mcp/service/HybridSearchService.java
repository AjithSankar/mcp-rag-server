package dev.ak.mcp.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HybridSearchService {

    private final VectorStore vectorStore;

    public HybridSearchService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public List<Document> search(String query) {

        System.out.println("=== MCP DEBUG: HYBRID SEARCH ===");
        System.out.println("Query: " + query);

        List<Document> docs = vectorStore.similaritySearch(query);

        docs.forEach(d ->
                System.out.println("Doc: " + d.getMetadata().get("source"))
        );

        return docs;
    }
}