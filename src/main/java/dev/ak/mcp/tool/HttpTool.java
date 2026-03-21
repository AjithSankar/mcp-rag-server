package dev.ak.mcp.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HttpTool {

    @McpTool(name = "get_weather", description = "Call external HTTP GET APIs to fetch weather information for a given location.")
    public String callApi(String location) {
        log.info("Calling external HTTP GET APIs");

        return switch (location.toLowerCase()) {
            case "new york" -> "Weather in New York: Sunny, 25°C";
            case "london" -> "Weather in London: Cloudy, 18°C";
            case "tokyo" -> "Weather in Tokyo: Rainy, 22°C";
            case "india" -> "Weather in India: Sunny, 27°C";
            default -> "Sorry, I don't have weather information for that location.";
        };

    }
}
