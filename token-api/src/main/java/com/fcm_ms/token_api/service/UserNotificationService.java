package com.fcm_ms.token_api.service;

import java.util.HashMap;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationService {

  public Message getMessage(String userExternalId) {
    return Message.builder()
      .setToken("fsaHzzFFQkiC1AA59hFqPk:APA91bFLWuoexvXOWQnjivY18E19sXMHNuxu2vQfpfWdFGHIBe8EuPxKetW-b4hnXG5hLDvfknJt3imYCIes3IUn0A85HDL8dd_N6jrmXtRobjDtpyvWj_s")
      .setNotification(
          Notification.builder()
          .setTitle("userExternalId: " + userExternalId)
          .setBody("Will this work?? Let's see...")
          .build()
          )
      .putAllData(new HashMap<>())
      .build();
  }
}
