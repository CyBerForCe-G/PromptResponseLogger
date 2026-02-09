package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromptRequestTest {

    @Test
    void setterAndGetter_shouldWorkCorrectly() {
        PromptRequest request = new PromptRequest();

        request.setPrompt("Hello, stream this");

        assertEquals("Hello, stream this", request.getPrompt());
    }

    @Test
    void equalsAndHashCode_shouldWorkCorrectly() {
        PromptRequest r1 = new PromptRequest();
        r1.setPrompt("Same prompt");

        PromptRequest r2 = new PromptRequest();
        r2.setPrompt("Same prompt");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void toString_shouldContainPrompt() {
        PromptRequest request = new PromptRequest();
        request.setPrompt("Test prompt");

        String toString = request.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("Test prompt"));
    }

    @Test
    void prompt_canBeNull() {
        PromptRequest request = new PromptRequest();

        assertNull(request.getPrompt());
    }
}
