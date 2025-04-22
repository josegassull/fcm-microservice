package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.mapper.TokenMapper;
import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;

  private final TokenMapper tokenMapper;

  public String registerToken(TokenRequest tokenRequest) {
    Token token = this.tokenMapper.toEntity(tokenRequest);

    Token savedToken = this.tokenRepository.save(token);

    return "Hello from TokenService!! Saved token from repository!: " + savedToken.toString();
  }
}
