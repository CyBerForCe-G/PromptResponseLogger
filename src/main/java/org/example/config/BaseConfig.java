package org.example.config;

import lombok.Data;

@Data
public abstract class BaseConfig {
    public String apiKey;
    public String baseUrl;
    public String model;
}
