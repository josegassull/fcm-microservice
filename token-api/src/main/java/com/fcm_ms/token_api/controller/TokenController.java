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
  public ResponseEntity<TokenResponse> registerNewToken(@Valid @RequestBody TokenRequest tokenRequest) {
    Boolean isCreated = this.tokenService.registerToken(tokenRequest);

    TokenResponse tr = new TokenResponse(
      "message", "description",
      "token-1230912i3912", 1000,
      new TokenResponse.Device(
        "UUID-123-456",
        "Android"
      ),
      new TokenResponse.HttpStatusDetail(
        HttpStatus.OK.name(), HttpStatus.OK.value()
      )
    );

    return new ResponseEntity<TokenResponse>(
      tr,
      HttpStatus.valueOf(tr.getHttpStatus().getCode())
    );
  }
}
