package org.example.interfaces;

import org.example.model.AIRequest;
import reactor.core.publisher.Mono;

public interface LLMProvider {
    String getName();
    Mono<String> generate(AIRequest aiRequest);
}
