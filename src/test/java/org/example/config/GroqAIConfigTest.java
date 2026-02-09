package org.example.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroqAIConfigTest {

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        GroqAIConfig config = new GroqAIConfig();

        config.setApiKey("groq-key");
        config.setBaseUrl("https://api.groq.com");
        config.setModel("llama3");

        assertEquals("groq-key", config.getApiKey());
        assertEquals("https://api.groq.com", config.getBaseUrl());
        assertEquals("llama3", config.getModel());
    }

    @Test
    void equalsAndHashCode_shouldIncludeSuperClassFields() {
        GroqAIConfig c1 = new GroqAIConfig();
        c1.setApiKey("key");
        c1.setBaseUrl("url");
        c1.setModel("model");

        GroqAIConfig c2 = new GroqAIConfig();
        c2.setApiKey("key");
        c2.setBaseUrl("url");
        c2.setModel("model");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void toString_shouldContainInheritedFields() {
        GroqAIConfig config = new GroqAIConfig();
        config.setApiKey("abc");
        config.setBaseUrl("http://localhost");
        config.setModel("groq-model");

        String str = config.toString();

        assertNotNull(str);
        assertTrue(str.contains("abc"));
        assertTrue(str.contains("localhost"));
        assertTrue(str.contains("groq-model"));
    }
}
