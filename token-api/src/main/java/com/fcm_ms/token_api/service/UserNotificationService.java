package com.fcm_ms.token_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.dto.BasicNotificationRequestDTO;
import com.fcm_ms.token_api.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class UserNotificationService {

  private final TokenRepository tokenRepository;

  public List<Message> getMessages(Integer userExternalId, BasicNotificationRequestDTO basicNotificationRequestDTO) {
    String notifTitle = basicNotificationRequestDTO.getTitle();
    String notifBody = basicNotificationRequestDTO.getBody();

    List<Token> userTokens = this.tokenRepository.findTokensByUserExternalId(userExternalId);

    return userTokens.stream()
      .map(token -> Message.builder()
        .setToken(token.getToken())
        .setNotification(Notification.builder()
          .setTitle(notifTitle)
          .setBody(notifBody)
          .build()
        )
        .build()
      )
      .collect(Collectors.toList());
  }
}
