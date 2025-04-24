#!/bin/sh

# live reload script for maven running on a docker container with error handling

export TERM=xterm

start_spring_boot() {
  echo "Starting springboot application..."
  mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" &
  SPRING_PID=$!

  sleep 111

  if ps -p $SPRING_PID > /dev/null; then
    echo "Springboot application started successfully with PID: $SPRING_PID"
    return 0
  else
    echo "ERROR: springboot application failed to start."
    return 1
  fi
}

restart_spring_boot() {
  if ! start_spring_boot; then
    echo "Initial springboot startup failed."
    exit 1
  fi
}

restart_spring_boot

echo "Start watching for file changes..."
while true; do
    if watch -d -t -g "ls -lR . | sha1sum"; then
      echo "Changes detected, recompiling..."
      mvn compile

      if [ -n "$SPRING_PID" ]; then
        echo "Stopping previous springboot instance (PID: $SPRING_PID)"
        kill $SPRING_PID
        wait $SPRING_PID 2> /dev/null
      fi

      restart_spring_boot
    fi
done
