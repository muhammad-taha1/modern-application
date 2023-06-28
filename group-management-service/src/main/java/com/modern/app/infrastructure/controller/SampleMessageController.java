package com.modern.app.infrastructure.controller;


import com.modern.app.application.inputs.SampleModelBehavior;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample-message")
public class SampleMessageController {

    private final SampleModelBehavior sampleModelBehaviorService;

    public SampleMessageController(SampleModelBehavior sampleModelBehaviorService) {
        this.sampleModelBehaviorService = sampleModelBehaviorService;
    }

    @GetMapping
    @Operation(summary = "get current message")
    public ResponseEntity<String> getCurrentMessage() {
        String message = sampleModelBehaviorService.getCurrentMessage();
        return ResponseEntity.ok(message);
    }

    @PostMapping
    @Operation(summary = "create new message")
    public ResponseEntity newMessage(@RequestParam String message) {
        sampleModelBehaviorService.saveCustomMessage(message);
        return ResponseEntity.accepted().build();
    }
}
