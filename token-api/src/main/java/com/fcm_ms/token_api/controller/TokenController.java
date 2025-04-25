package com.fcm_ms.token_api.controller;

import java.util.HashMap;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.dto.TokenResponse;
import com.fcm_ms.token_api.service.TokenService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenService;

  @PostMapping("register")
  public ResponseEntity<TokenResponse> registerNewToken(@Valid @RequestBody TokenRequest tokenRequest) {
    Boolean isCreated = this.tokenService.registerToken(tokenRequest);
    TokenResponse tokenResponse = this.tokenService.getTokenResponse(tokenRequest, isCreated);

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
      String response = FirebaseMessaging.getInstance().sendAsync(message).get();
      System.out.println("{FIREBASEEE} " + response);
    } catch (Exception ex) {}

    return new ResponseEntity<>(
      tokenResponse,
      tokenResponse._getHttpStatus()
    );
  }
}
