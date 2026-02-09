package org.example.interfaces;

import reactor.core.publisher.Mono;

public interface LLMService {
    Mono<String> generateResponse(String prompt);
}
