package com.fcm_ms.token_api.controller;

import java.util.HashMap;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notify/user")
public class UserNotificationController {

  @PostMapping("{user_external_id}")
  public String notifyUser(@PathVariable("user_external_id") String userExternalId) {
    String response = "Notification not sent";

    /* TODO
     *  - get list of tokens of user with externalId X
     *  - create Notification with hardcoded title and body
     *  - send to all tokens
     * */
    try {
      Message message = Message.builder()
        .setToken("fsaHzzFFQkiC1AA59hFqPk:APA91bFLWuoexvXOWQnjivY18E19sXMHNuxu2vQfpfWdFGHIBe8EuPxKetW-b4hnXG5hLDvfknJt3imYCIes3IUn0A85HDL8dd_N6jrmXtRobjDtpyvWj_s")
        .setNotification(
            Notification.builder()
            .setTitle("Forced notification")
            .setBody("Will this work?? Let's see...")
            .build()
            )
        .putAllData(new HashMap<>())
        .build();
      response = FirebaseMessaging.getInstance().sendAsync(message).get();
    } catch (Exception ex) {}

    return response;
  }

}
