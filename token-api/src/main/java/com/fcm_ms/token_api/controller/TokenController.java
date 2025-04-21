package com.fcm_ms.token_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

import com.fcm_ms.token_api.dto.TokenRequest;
import com.fcm_ms.token_api.service.TokenService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenService;

  @PostMapping("register")
  public String registerNewToken(@RequestBody TokenRequest tokenRequest) {
    return "Hello register token. Request: " + tokenService.registerToken(tokenRequest);
  }
}
