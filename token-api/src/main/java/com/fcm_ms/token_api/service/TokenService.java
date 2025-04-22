package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.mapper.TokenMapper;
import com.fcm_ms.token_api.entity.Token;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenMapper tokenMapper;

  public String registerToken(TokenRequest tokenRequest) {
    Token token = this.tokenMapper.toEntity(tokenRequest);

    /* TODO tokenRepository */

    return "Hello from TokenService!! From mapper: " + token.toString();
  }
}
