package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.Instant;

@Component
public class JsonFileLogger {

    @Value("${logging.file.output-dir}")
    private String outputDir;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void log(JsonNode response) {
        try {
            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = "response-" + Instant.now().toEpochMilli() + ".json";
            File file = new File(dir, filename);

            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, response);

        } catch (Exception e) {
            throw new RuntimeException("Failed to write JSON log", e);
        }
    }
}
