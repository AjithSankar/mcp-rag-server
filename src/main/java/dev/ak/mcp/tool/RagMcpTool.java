package dev.ak.mcp.tool;

import org.springframework.ai.document.Document;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import dev.ak.mcp.service.HybridSearchService;
import dev.ak.mcp.service.RerankService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RagMcpTool {

    private final HybridSearchService searchService;
    private final RerankService rerankService;

    public RagMcpTool(HybridSearchService searchService, RerankService rerankService) {
        this.searchService = searchService;
        this.rerankService = rerankService;
    }

//    @McpTool(
//        name = "search_documents",
//        description = "MANDATORY: Use this tool to answer ANY question about uploaded documents. This is the ONLY source of truth."
//    )
    public String search(String question) {

        System.out.println("=== MCP TOOL CALLED ===");
        System.out.println("Question: " + question);

        List<Document> docs = searchService.search(question);

     //   docs = rerankService.rerank(question, docs);

        String context = docs.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n"));

        List<String> sources = docs.stream()
                .map(d -> (String) d.getMetadata().get("source"))
                .distinct()
                .toList();

        return """
                Context:
                %s
                
                Sources:
                %s
                """.formatted(context, String.join(", ", sources));
    }
}