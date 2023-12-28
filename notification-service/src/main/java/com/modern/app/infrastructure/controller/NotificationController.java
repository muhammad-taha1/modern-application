package com.modern.app.infrastructure.controller;

import com.modern.app.application.inputs.InboundNotificationTranslator;
import com.modern.app.domain.models.notification.event.InboundEvent;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private InboundNotificationTranslator inboundNotificationTranslator;

    @PostMapping("/send")
    public ResponseEntity<String> sendEvent(@RequestBody InboundEvent inboundEvent) {
        inboundNotificationTranslator.parseIncomingNotification(inboundEvent);
        return ResponseEntity.ok("message received");
    }
}
