package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIStreamRequestTest {

    @Test
    void constructorAndGetters_shouldWorkCorrectly() {
        AIStreamRequest.Message message =
                new AIStreamRequest.Message("user", "Hello stream");

        AIStreamRequest request = new AIStreamRequest(
                "gpt-4",
                List.of(message),
                true
        );

        assertEquals("gpt-4", request.getModel());
        assertEquals(1, request.getMessages().size());
        assertEquals("user", request.getMessages().get(0).getRole());
        assertEquals("Hello stream", request.getMessages().get(0).getContent());
        assertTrue(request.isStream());
    }

    @Test
    void equalsAndHashCode_shouldWorkCorrectly() {
        AIStreamRequest.Message m1 =
                new AIStreamRequest.Message("user", "Hi");

        AIStreamRequest.Message m2 =
                new AIStreamRequest.Message("user", "Hi");

        AIStreamRequest r1 =
                new AIStreamRequest("model", List.of(m1), true);

        AIStreamRequest r2 =
                new AIStreamRequest("model", List.of(m2), true);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void toString_shouldContainImportantFields() {
        AIStreamRequest.Message message =
                new AIStreamRequest.Message("assistant", "Streaming...");

        AIStreamRequest request =
                new AIStreamRequest("model-x", List.of(message), true);

        String toString = request.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("model-x"));
        assertTrue(toString.contains("Streaming"));
        assertTrue(toString.contains("stream"));
    }

    @Test
    void message_equalsAndHashCode_shouldWorkCorrectly() {
        AIStreamRequest.Message m1 =
                new AIStreamRequest.Message("system", "You are helpful");

        AIStreamRequest.Message m2 =
                new AIStreamRequest.Message("system", "You are helpful");

        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
    }

    @Test
    void streamFlag_shouldSupportFalse() {
        AIStreamRequest request =
                new AIStreamRequest("model", List.of(), false);

        assertFalse(request.isStream());
    }
}
