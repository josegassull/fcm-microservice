#!/usr/bin/env sh

# live reload script for maven running on a docker container with error handling

APP_DIR="/home/fcm-ms/token-api"
JAR_NAME="app.jar"

cd "$APP_DIR"

./mvnw
exit 0
