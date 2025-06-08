package com.fcm_ms.token_api.service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.dto.NotificationRequestDTO;
import com.fcm_ms.token_api.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class UserNotificationService {

  private final TokenRepository tokenRepository;

  public List<Message> getMessages(Integer userExternalId, NotificationRequestDTO notificationRequestDTO) {
    String notifTitle = notificationRequestDTO.getTitle();
    String notifBody = notificationRequestDTO.getBody();

    List<Token> userTokens = this.tokenRepository.findTokensByUserExternalId(userExternalId);
    Map<String, String> additionalData = new HashMap<>();

    if (notificationRequestDTO.getData() != null)
      for (Map.Entry<String, String> entry : notificationRequestDTO.getData().entrySet())
        additionalData.put(entry.getKey(), entry.getValue());

    return userTokens.stream()
      .map(token -> Message.builder()
        .setToken(token.getToken())
        .setNotification(Notification.builder()
          .setTitle(notifTitle)
          .setBody(notifBody)
          .build()
        )
        .putAllData(additionalData)
        .build()
      )
      .collect(Collectors.toList());
  }
}
