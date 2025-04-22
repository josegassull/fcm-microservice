package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.mapper.TokenMapper;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenMapper tokenMapper;

  public String registerToken(TokenRequest tokenRequest) {
    return "Hello from TokenService!! From mapper: " + this.tokenMapper.toEntity(tokenRequest).toString();
  }
}
