package com.fcm_ms.token_api.controller;

import java.util.HashMap;

import com.fcm_ms.token_api.service.UserDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
  //todo: sacar el cross origins
  @CrossOrigin(origins = "*")
  public ResponseEntity<TokenResponseDTO> registerNewToken(@Valid @RequestBody TokenRequestDTO tokenRequest) {
    Boolean isCreated = this.tokenService.registerToken(tokenRequest);
    TokenResponseDTO tokenResponse = this.tokenService.getTokenResponse(tokenRequest, isCreated);

    return new ResponseEntity<>(
      tokenResponse,
      tokenResponse._getHttpStatus()
    );
  }
}
