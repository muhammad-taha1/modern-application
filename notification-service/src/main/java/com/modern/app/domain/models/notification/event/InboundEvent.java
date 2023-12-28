package com.modern.app.domain.models.notification.event;

import java.util.Map;

public record InboundEvent(String name, Map<String, String> payload) {
}
