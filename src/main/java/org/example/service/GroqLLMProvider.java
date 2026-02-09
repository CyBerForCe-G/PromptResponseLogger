package org.example.service;

import org.example.config.GroqAIConfig;
import org.example.constants.ApplicationConstants;
import org.example.interfaces.LLMProvider;
import org.example.model.AIRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GroqLLMProvider implements LLMProvider {

    private final GroqAIConfig groqAIConfig;
    private final WebClient webClient;

    public GroqLLMProvider(GroqAIConfig groqAIConfig, WebClient.Builder webClient){
        this.groqAIConfig = groqAIConfig;
        this.webClient = webClient
                .baseUrl(groqAIConfig.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + groqAIConfig.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public String getName() {
        return "groq";
    }

    @Override
    public Mono<String> generate(AIRequest aiRequest) {
        AIRequest provideRequest = new AIRequest(groqAIConfig.getModel(), aiRequest.getMessages());
        return webClient.post()
                .uri(ApplicationConstants.COMPLETION_URL)
                .bodyValue(provideRequest)
                .retrieve()
                .bodyToMono(String.class);
    }
}
