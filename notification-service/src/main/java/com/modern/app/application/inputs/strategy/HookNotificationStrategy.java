package com.modern.app.application.inputs.strategy;

import com.modern.app.domain.models.NotificationSender;
import com.modern.app.domain.models.notification.NotificationStrategy;
import com.modern.app.domain.models.notification.configuration.NotificationConfig;
import com.modern.app.domain.models.notification.event.InboundEvent;
import com.modern.app.application.outputs.sender.HookNotificationSender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HookNotificationStrategy implements NotificationStrategy {

    private HookNotificationSender hookNotificationSender;

    @Override
    public String getContent(NotificationConfig notificationConfig, InboundEvent inboundEvent) {
        return replaceKeywords(notificationConfig.template(), inboundEvent.payload());
    }

    @Override
    public List<String> getRecipients(NotificationConfig notificationConfig, InboundEvent inboundEvent) {
        return new ArrayList<>();
    }

    @Override
    public NotificationSender getNotificationSender() {
        return hookNotificationSender;
    }


    private String replaceKeywords(String template, Map<String, String> replacements) {
        StringBuilder result = new StringBuilder(template);

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            String keyword = "{" + entry.getKey() + "}";
            String value = entry.getValue();

            int startIndex = result.indexOf(keyword);
            while (startIndex != -1) {
                int endIndex = startIndex + keyword.length();
                result.replace(startIndex, endIndex, value);
                startIndex = result.indexOf(keyword, startIndex + value.length());
            }
        }

        return result.toString();
    }
}
