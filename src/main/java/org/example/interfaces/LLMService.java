package org.example.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Mono;

public interface LLMService {
    Mono<String> generateResponse(String prompt);
}
