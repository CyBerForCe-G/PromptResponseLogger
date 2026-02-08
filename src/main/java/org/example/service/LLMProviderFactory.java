package org.example.service;

import org.example.interfaces.LLMProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LLMProviderFactory {

    private final Map<String, LLMProvider> providers;

    public LLMProviderFactory(List<LLMProvider> providerList) {
        this.providers = providerList.stream()
                .collect(Collectors.toMap(LLMProvider::getName, p -> p));
    }

    public LLMProvider getProvider(String name) {
        LLMProvider provider = providers.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("Unsupported LLM provider: " + name);
        }
        return provider;
    }
}
