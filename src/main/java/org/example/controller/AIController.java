package org.example.controller;

import org.example.constants.ApplicationConstants;
import org.example.interfaces.LLMService;
import org.example.interfaces.LLMStreamService;
import org.example.model.PromptRequest;
import org.example.util.JsonFileLogger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApplicationConstants.API_ENDPOINT)
public class AIController {

    private final LLMService llmService;
    private final JsonFileLogger jsonFileLogger;
    private final LLMStreamService llmStreamService;

    public AIController(LLMService llmService,
                        JsonFileLogger jsonFileLogger,
                        LLMStreamService llmStreamService) {
        this.llmService = llmService;
        this.jsonFileLogger = jsonFileLogger;
        this.llmStreamService = llmStreamService;
    }

    @PostMapping("/prompt")
    public Mono<String> prompt(@RequestBody PromptRequest request) {
        Mono<String> response = llmService.generateResponse(request.getPrompt());
//        jsonFileLogger.log(response);
        return response;
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestBody PromptRequest promptRequest){
        StringBuilder fullResponse = new StringBuilder();
        return llmStreamService.generate(promptRequest.getPrompt())
                .concatWith(Flux.just("[DONE]"))
                .onErrorResume(error -> {System.out.print(error.getMessage());
                        return Flux.just("[ERROR]");
                });
//                .doOnNext(token -> {
//                    System.out.print(token);
//                    fullResponse.append(token);
//                })
//                .doOnComplete(() -> {System.out.print("Full Response: "+fullResponse);});

    }
}
