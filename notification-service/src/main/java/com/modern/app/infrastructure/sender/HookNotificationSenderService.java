package com.modern.app.infrastructure.sender;

import com.modern.app.application.outputs.sender.HookNotificationSender;
import com.modern.app.domain.models.notification.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class HookNotificationSenderService implements HookNotificationSender {
    @Override
    public Notification send(String content, List<String> recipients) {
        Notification notification = new Notification(content, recipients);
        log.info("sending hook notification content: {}", notification);
        return notification;
    }
}
