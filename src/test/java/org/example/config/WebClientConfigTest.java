package org.example.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = WebClientConfig.class)
class WebClientConfigTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void webClientBuilderBean_shouldBeCreated() {
        assertNotNull(webClientBuilder);
    }

    @Test
    void webClientBuilder_shouldBuildWebClient() {
        WebClient webClient = webClientBuilder.build();
        assertNotNull(webClient);
    }
}
