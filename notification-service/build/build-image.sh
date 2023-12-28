#!/usr/bin/env bash

mvn clean install -DskipTests
docker build -t modern-app/notification-service .

