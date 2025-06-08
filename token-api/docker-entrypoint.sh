#!/bin/sh

# live reload script for maven running on a docker container with error handling

APP_DIR="/home/fcm-ms/token-api"
JAR_NAME="app.jar"

cd "$APP_DIR"

echo "Starting file watcher for live reload..."

start_app() {
  echo "Building the app..."
  ./mvnw clean package -DskipTests
  echo "Starting the app..."
  java -jar target/*.jar &
  APP_PID=$!
}

start_app

while true; do
  # watch for file changes
  inotifywait -r -e modify,create,delete ./src
  echo "Changes detected. Restarting the application..."

  kill $APP_PID
  wait $APP_PID 2> /dev/null

  # restart
  start_app
done

