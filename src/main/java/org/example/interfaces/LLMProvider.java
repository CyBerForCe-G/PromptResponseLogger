package org.example.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.model.AIRequest;

public interface LLMProvider {
    String getName();
    JsonNode generate(AIRequest aiRequest);
}
