package org.example.service.StreamServices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.GroqAIConfig;
import org.example.constants.ApplicationConstants;
import org.example.interfaces.LLMStreamService;
import org.example.model.AIStreamRequest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class GroqLLMStreamService implements LLMStreamService {

    private final WebClient webClient;
    private final GroqAIConfig groqAIConfig;
    private final ObjectMapper mapper = new ObjectMapper();

    public GroqLLMStreamService(GroqAIConfig groqAIConfig, WebClient.Builder builder){
        this.groqAIConfig = groqAIConfig;
        this.webClient = builder
                .baseUrl(groqAIConfig.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + groqAIConfig.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @Override
    public Flux<String> generate(String prompt) {
        AIStreamRequest streamRequest = new AIStreamRequest(groqAIConfig.getModel(),
                List.of(new AIStreamRequest.Message(ApplicationConstants.API_ROLE_USER, prompt)), true);
        return webClient.post()
                .uri(ApplicationConstants.COMPLETION_URL)
                .bodyValue(streamRequest)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .map(this::bufferToString)
                .flatMap(this::parseSse);
    }

    private String bufferToString(DataBuffer buffer) {
        byte[] bytes = new byte[buffer.readableByteCount()];
        buffer.read(bytes);
        DataBufferUtils.release(buffer);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private Flux<String> parseSse(String chunk) {
        return Flux.fromArray(chunk.split("\n"))
                .filter(line -> line.startsWith("data:"))
                .map(line -> line.replace("data:", "").trim())
                .filter(data -> !data.equals("[DONE]"))
                .handle((json, sink) -> {
                    try {
                        JsonNode node = mapper.readTree(json);
                        JsonNode content = node.path("choices")
                                .get(0)
                                .path("delta")
                                .path("content");

                        if (!content.isMissingNode()) {
                            sink.next(content.asText());
                        }
                    } catch (Exception ignored) {
                        // silently skip malformed chunks
                        System.out.println("WARN : exception ignored " + ignored.getMessage());
                    }
                });
    }
}