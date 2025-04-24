package com.fcm_ms.token_api.controller;

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
  public ResponseEntity<String> registerNewToken(@Valid @RequestBody TokenRequest tokenRequest) {
    Boolean isCreated = this.tokenService.registerToken(tokenRequest);
    String tokenResponse = this.tokenService.getTokenResponse(tokenRequest, isCreated);

    return new ResponseEntity<String>(
      tokenResponse,
      HttpStatus.OK
    );
  }
}
