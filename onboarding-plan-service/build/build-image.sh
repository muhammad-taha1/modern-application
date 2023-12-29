#!/usr/bin/env bash

mvn clean install -Dspring.profiles.active=prod -DskipTests
docker build -t modern-app/onboarding-plan-service .

