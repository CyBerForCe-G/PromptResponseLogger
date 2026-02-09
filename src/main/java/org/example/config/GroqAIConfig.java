package org.example.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Configuration
@ConfigurationProperties(prefix = "llm.groq")
public class GroqAIConfig extends BaseConfig{
}
