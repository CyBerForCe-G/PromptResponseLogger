package org.example.service;

import org.example.config.GroqAIConfig;
import org.example.model.AIRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroqLLMProviderTest {

    private GroqLLMProvider provider;

    @BeforeEach
    void setUp() {
        // --- Config ---
        GroqAIConfig config = new GroqAIConfig();
        config.setApiKey("test-key");
        config.setBaseUrl("http://localhost");
        config.setModel("test-model");

        // --- Mock HTTP layer ---
        ExchangeFunction exchangeFunction = request -> {
            // You can assert request details here if you want
            return Mono.just(
                    ClientResponse
                            .create(org.springframework.http.HttpStatus.OK)
                            .header("Content-Type", "application/json")
                            .body("{\"result\":\"hello\"}")
                            .build()
            );
        };

        WebClient webClient = WebClient.builder()
                .exchangeFunction(exchangeFunction)
                .build();

        provider = new GroqLLMProvider(config, WebClient.builder()
                .exchangeFunction(exchangeFunction));
    }

    @Test
    void getName_shouldReturnGroq() {
        assertEquals("groq", provider.getName());
    }

    @Test
    void generate_shouldReturnResponseBody() {
        AIRequest request = new AIRequest(
                "ignored",
                List.of(new AIRequest.Message("user", "Hello"))
        );

        Mono<String> result = provider.generate(request);

        StepVerifier.create(result)
                .expectNext("{\"result\":\"hello\"}")
                .verifyComplete();
    }
}
