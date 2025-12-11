package com.fcm_ms.token_api.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.repository.TokenRepository;
import com.fcm_ms.token_api.dto.DataMessageRequestDTO;

@Service
@RequiredArgsConstructor
public class UserDataMessageService {

  private final TokenRepository tokenRepository;

  public MulticastMessage getMulticastDataMessage(
    DataMessageRequestDTO dataMessageRequestDTO) {

    Map<String, String> data = new HashMap<>();

    for (Map.Entry<String, String> entry : dataMessageRequestDTO.getData().entrySet())
      data.put(entry.getKey(), entry.getValue());

    return MulticastMessage.builder()
      .addToken("test")
      .putAllData(data)
      .build();
  }
}

