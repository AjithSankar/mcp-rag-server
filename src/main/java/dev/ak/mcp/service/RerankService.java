package dev.ak.mcp.service;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RerankService {

    public List<Document> rerank(String query, List<Document> docs) {

        // Simple placeholder (you can plug LLM rerank later)
        return docs.stream().limit(3).toList();
    }
}