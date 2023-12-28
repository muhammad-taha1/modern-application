package com.modern.app.infrastructure.repository;

import com.modern.app.application.outputs.NotificationConfigRepository;
import com.modern.app.domain.models.notification.configuration.NotificationConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoNotificationConfigRepository extends MongoRepository<NotificationConfig, String>, NotificationConfigRepository {

}
