package com.fcm_ms.token_api.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import com.fcm_ms.token_api.dto.TokenRequestDTO;
import com.fcm_ms.token_api.dto.TokenResponseDTO;
import com.fcm_ms.token_api.service.TokenService;

@RestController
@RequestMapping("api/token")
@RequiredArgsConstructor
public class TokenController {

  private final TokenService tokenService;

  @PostMapping("register")
  public ResponseEntity<TokenResponseDTO> registerNewToken(@Valid @RequestBody TokenRequestDTO tokenRequest) {
    Boolean isCreated = this.tokenService.registerToken(tokenRequest);
    TokenResponseDTO tokenResponse = this.tokenService.getTokenResponse(tokenRequest, isCreated);

    return new ResponseEntity<>(
      tokenResponse,
      tokenResponse._getHttpStatus()
    );
  }
}
