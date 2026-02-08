package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.constants.ApplicationConstants;
import org.example.interfaces.LLMProvider;
import org.example.interfaces.LLMService;
import org.example.model.AIRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class LLMServiceImpl implements LLMService {

    private final LLMProviderFactory factory;

    @Value("${llm.active-provider}")
    private String activeProvider;

    public LLMServiceImpl(LLMProviderFactory factory) {
        this.factory = factory;
    }

    @Override
    public JsonNode generateResponse(String prompt) {
        LLMProvider provider = factory.getProvider(activeProvider);
        AIRequest aiRequest = new AIRequest(null, List.of(new AIRequest.Message(ApplicationConstants.API_ROLE_USER, prompt)));
        return provider.generate(aiRequest);
    }
}
