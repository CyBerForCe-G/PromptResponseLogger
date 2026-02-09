package org.example.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseConfigTest {

    static class TestBaseConfig extends BaseConfig {
    }

    @Test
    void gettersAndSetters_shouldWorkCorrectly() {
        TestBaseConfig config = new TestBaseConfig();

        config.setApiKey("test-api-key");
        config.setBaseUrl("https://api.test.com");
        config.setModel("test-model");

        assertEquals("test-api-key", config.getApiKey());
        assertEquals("https://api.test.com", config.getBaseUrl());
        assertEquals("test-model", config.getModel());
    }

    @Test
    void equalsAndHashCode_shouldWorkCorrectly() {
        TestBaseConfig c1 = new TestBaseConfig();
        c1.setApiKey("key");
        c1.setBaseUrl("url");
        c1.setModel("model");

        TestBaseConfig c2 = new TestBaseConfig();
        c2.setApiKey("key");
        c2.setBaseUrl("url");
        c2.setModel("model");

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void toString_shouldContainImportantFields() {
        TestBaseConfig config = new TestBaseConfig();
        config.setApiKey("key123");
        config.setBaseUrl("http://localhost");
        config.setModel("gpt-x");

        String toString = config.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("key123"));
        assertTrue(toString.contains("localhost"));
        assertTrue(toString.contains("gpt-x"));
    }

    @Test
    void defaultValues_shouldBeNull() {
        TestBaseConfig config = new TestBaseConfig();

        assertNull(config.getApiKey());
        assertNull(config.getBaseUrl());
        assertNull(config.getModel());
    }
}
