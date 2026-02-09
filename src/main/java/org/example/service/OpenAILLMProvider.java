package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.config.OpenAIConfig;
import org.example.constants.ApplicationConstants;
import org.example.interfaces.LLMProvider;
import org.example.model.AIRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OpenAILLMProvider implements LLMProvider {
    private final WebClient webClient;
    private final OpenAIConfig openAIConfig;

    public OpenAILLMProvider(WebClient.Builder webClient, OpenAIConfig openAIConfig){
        this.openAIConfig = openAIConfig;
        this.webClient = webClient
                .baseUrl(openAIConfig.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + openAIConfig.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
    @Override
    public String getName() {
        return "openai";
    }

    @Override
    public Mono<String> generate(AIRequest aiRequest) {
        AIRequest provideRequest = new AIRequest(openAIConfig.getModel(), aiRequest.getMessages());
        return webClient.post()
                .uri(ApplicationConstants.COMPLETION_URL)
                .bodyValue(provideRequest)
                .retrieve()
                .bodyToMono(String.class);
    }
}
