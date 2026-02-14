//package org.example.controller;
//
//import org.example.config.SecurityConfig;
//import org.example.constants.ApplicationConstants;
//import org.example.interfaces.LLMService;
//import org.example.interfaces.LLMStreamService;
//import org.example.model.PromptRequest;
//import org.example.util.JsonFileLogger;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import static org.mockito.ArgumentMatchers.anyString;
//
//@WebFluxTest(controllers = AIController.class)
//@Import(SecurityConfig.class)
//class AIControllerTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @MockBean
//    private LLMService llmService;
//
//    @MockBean
//    private LLMStreamService llmStreamService;
//
//    @MockBean
//    private JsonFileLogger jsonFileLogger;
//
//    // ---------- /prompt TEST ----------
//
//    @Test
//    @WithMockUser
//    void prompt_shouldReturnResponse() {
//        Mockito.when(llmService.generateResponse(anyString()))
//                .thenReturn(Mono.just("Hello from LLM"));
//
//        PromptRequest request = new PromptRequest();
//        request.setPrompt("Hi");
//
//        webTestClient.post()
//                .uri(ApplicationConstants.API_ENDPOINT + "/prompt")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(String.class)
//                .isEqualTo("Hello from LLM");
//    }
//
//    // ---------- /stream TEST ----------
//
//    @Test
//    @WithMockUser
//    void stream_shouldReturnStreamingResponse() {
//        Flux<String> mockStream = Flux.just("Hello ", "World", "!");
//
//        Mockito.when(llmStreamService.generate(anyString()))
//                .thenReturn(mockStream);
//
//        PromptRequest request = new PromptRequest();
//        request.setPrompt("Stream test");
//
//        webTestClient.post()
//                .uri(ApplicationConstants.API_ENDPOINT + "/stream")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.TEXT_EVENT_STREAM)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
//                .expectBodyList(String.class)
//                .contains("Hello ", "World", "!");
//    }
//}
