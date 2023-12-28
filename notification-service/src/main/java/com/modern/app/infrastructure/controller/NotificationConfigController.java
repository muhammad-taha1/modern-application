package com.modern.app.infrastructure.controller;

import com.modern.app.application.inputs.NotificationConfigService;
import com.modern.app.domain.models.notification.configuration.NotificationConfig;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification-config")
@AllArgsConstructor
public class NotificationConfigController {

    private NotificationConfigService notificationConfigService;

    @PostMapping
    public ResponseEntity<String> saveNotificationConfig(@RequestBody NotificationConfig notificationConfig) {
        notificationConfigService.saveNotificationConfig(notificationConfig);
        return ResponseEntity.ok("saved notification config");
    }

    @GetMapping
    public ResponseEntity<NotificationConfig> getNotificationConfigByName(@RequestParam String name) {
        return ResponseEntity.ok(notificationConfigService.getNotificationConfigByName(name));
    }
}
