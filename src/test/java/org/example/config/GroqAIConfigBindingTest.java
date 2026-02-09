package org.example.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = GroqAIConfigBindingTest.TestConfig.class)
@TestPropertySource(properties = {
        "llm.groq.api-key=test-key",
        "llm.groq.base-url=https://api.groq.com",
        "llm.groq.model=llama3-70b"
})
class GroqAIConfigBindingTest {

    @Autowired
    private GroqAIConfig groqAIConfig;

    @Test
    void configurationProperties_shouldBindCorrectly() {
        assertNotNull(groqAIConfig);

        assertEquals("test-key", groqAIConfig.getApiKey());
        assertEquals("https://api.groq.com", groqAIConfig.getBaseUrl());
        assertEquals("llama3-70b", groqAIConfig.getModel());
    }

    @EnableConfigurationProperties(GroqAIConfig.class)
    static class TestConfig {
    }
}
