package org.example.controller;


import com.fasterxml.jackson.databind.JsonNode;
import org.example.constants.ApplicationConstants;
import org.example.interfaces.LLMService;
import org.example.model.PromptRequest;
import org.example.util.JsonFileLogger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApplicationConstants.API_ENDPOINT)
public class AIController {

    private final LLMService llmService;
    private final JsonFileLogger jsonFileLogger;

    public AIController(LLMService llmService,
                        JsonFileLogger jsonFileLogger) {
        this.llmService = llmService;
        this.jsonFileLogger = jsonFileLogger;
    }

    @PostMapping("/prompt")
    public JsonNode prompt(@RequestBody PromptRequest request) {
        JsonNode response = llmService.generateResponse(request.getPrompt());
        jsonFileLogger.log(response);
        return response;
    }
}
