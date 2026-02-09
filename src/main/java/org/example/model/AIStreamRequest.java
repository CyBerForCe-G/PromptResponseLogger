package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AIStreamRequest {
    private String model;
    private List<Message> messages;
    private boolean stream;

    @Data
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
    }
}
