package org.example.interfaces;

import com.fasterxml.jackson.databind.JsonNode;

public interface LLMService {
    JsonNode generateResponse(String prompt);
}
