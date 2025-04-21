package com.fcm_ms.token_api.service;

import org.springframework.stereotype.Service;

import com.fcm_ms.token_api.dto.TokenRequest;

@Service
public class TokenService {

  public String registerToken(TokenRequest tokenRequest) {
    return "Hello from TokenService!! " + tokenRequest.toString();
  }
}
