package com.fcm_ms.token_api.service;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.repository.TokenRepository;
import com.fcm_ms.token_api.dto.DataMessageRequestDTO;

@Service
@RequiredArgsConstructor
public class UserDataMessageService {

  private final TokenRepository tokenRepository;

  public MulticastMessage getMulticastDataMessage(
    Integer userExternalId,
    DataMessageRequestDTO dataMessageRequestDTO) {

    Collection<String> tokenCollection = this.tokenRepository
      .findTokensByUserExternalId(userExternalId)
      .stream()
      .map(Token::getToken)
      .limit(500) /* firebase allows max 500 tokens */
      .collect(Collectors.toList());

    Map<String, String> data = new HashMap<>();

    for (Map.Entry<String, String> entry : dataMessageRequestDTO.getData().entrySet())
      data.put(entry.getKey(), entry.getValue());

    return MulticastMessage.builder()
      .addAllTokens(tokenCollection)
      .putAllData(data)
      .build();
  }
}

