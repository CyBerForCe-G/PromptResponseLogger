package org.example.interfaces;

import reactor.core.publisher.Flux;

public interface LLMStreamService {
    Flux<String> generate(String prompt);
}
