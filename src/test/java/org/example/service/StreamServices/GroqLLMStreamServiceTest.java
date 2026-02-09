package org.example.service.StreamServices;

import org.example.config.GroqAIConfig;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;

class GroqLLMStreamServiceTest {

    @Test
    void generate_shouldStreamTokensFromSseResponse() {
        // ---------- Mock SSE response ----------
        String sse =
                "data: {\"choices\":[{\"delta\":{\"content\":\"Hello \"}}]}\n\n" +
                        "data: {\"choices\":[{\"delta\":{\"content\":\"World\"}}]}\n\n" +
                        "data: [DONE]\n\n";

        DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
        Flux<DataBuffer> body = Flux.just(factory.wrap(sse.getBytes(StandardCharsets.UTF_8)));

        ClientResponse mockResponse = ClientResponse
                .create(HttpStatus.OK)
                .header("Content-Type", "text/event-stream")
                .body(body)
                .build();

        ExchangeFunction exchangeFunction = request -> Flux.just(mockResponse).single();

        WebClient webClient = WebClient.builder()
                .exchangeFunction(exchangeFunction)
                .build();

        // ---------- Config ----------
        GroqAIConfig config = new GroqAIConfig();
        config.setApiKey("test-key");
        config.setBaseUrl("http://fake-url");
        config.setModel("fake-model");

        // ---------- Service under test ----------
        GroqLLMStreamService service =
                new GroqLLMStreamService(config, webClient.mutate());

        // ---------- Verify ----------
        StepVerifier.create(service.generate("Hi"))
                .expectNext("Hello ")
                .expectNext("World")
                .verifyComplete();
    }

    @Test
    void generate_shouldIgnoreMalformedJson() {
        String sse =
                "data: {invalid-json}\n\n" +
                        "data: {\"choices\":[{\"delta\":{\"content\":\"OK\"}}]}\n\n";

        DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
        Flux<DataBuffer> body = Flux.just(factory.wrap(sse.getBytes(StandardCharsets.UTF_8)));

        ClientResponse response = ClientResponse
                .create(HttpStatus.OK)
                .body(body)
                .build();

        ExchangeFunction exchangeFunction = request -> Flux.just(response).single();

        WebClient webClient = WebClient.builder()
                .exchangeFunction(exchangeFunction)
                .build();

        GroqAIConfig config = new GroqAIConfig();
        config.setApiKey("key");
        config.setBaseUrl("url");
        config.setModel("model");

        GroqLLMStreamService service =
                new GroqLLMStreamService(config, webClient.mutate());

        StepVerifier.create(service.generate("test"))
                .expectNext("OK")
                .verifyComplete();
    }
}
