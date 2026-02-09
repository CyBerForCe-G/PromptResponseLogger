package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AIRequestTest {

    @Test
    void constructor_shouldSetFieldsCorrectly() {
        AIRequest.Message message =
                new AIRequest.Message("user", "Hello");

        AIRequest request =
                new AIRequest("gpt-4", List.of(message));

        assertEquals("gpt-4", request.getModel());
        assertEquals(1, request.getMessages().size());
        assertEquals("user", request.getMessages().get(0).getRole());
        assertEquals("Hello", request.getMessages().get(0).getContent());
    }

    @Test
    void setters_shouldUpdateFields() {
        AIRequest request =
                new AIRequest("model-1", List.of());

        request.setModel("model-2");

        AIRequest.Message message =
                new AIRequest.Message("assistant", "Hi there");
        request.setMessages(List.of(message));

        assertEquals("model-2", request.getModel());
        assertEquals(1, request.getMessages().size());
        assertEquals("assistant", request.getMessages().get(0).getRole());
    }

    @Test
    void equalsAndHashCode_shouldWorkCorrectly() {
        AIRequest.Message message1 =
                new AIRequest.Message("user", "Hello");

        AIRequest.Message message2 =
                new AIRequest.Message("user", "Hello");

        AIRequest request1 =
                new AIRequest("model-x", List.of(message1));

        AIRequest request2 =
                new AIRequest("model-x", List.of(message2));

        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void toString_shouldNotBeNull() {
        AIRequest.Message message =
                new AIRequest.Message("user", "Test");

        AIRequest request =
                new AIRequest("model-test", List.of(message));

        assertNotNull(request.toString());
        assertTrue(request.toString().contains("model-test"));
    }

    @Test
    void message_class_shouldWorkIndependently() {
        AIRequest.Message message =
                new AIRequest.Message("system", "You are an AI");

        assertEquals("system", message.getRole());
        assertEquals("You are an AI", message.getContent());

        message.setContent("Updated content");
        assertEquals("Updated content", message.getContent());
    }
}
