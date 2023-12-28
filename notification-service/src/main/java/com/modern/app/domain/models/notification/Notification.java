package com.modern.app.domain.models.notification;

import java.util.List;

public record Notification(String content, List<String> recipients) {
}
