package com.fcm_ms.token_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fcm_ms.token_api.dto.TokenRequest;

@RestController
@RequestMapping("api")
public class TokenController {

  @PostMapping("register")
  public String registerNewToken(@RequestBody TokenRequest tokenRequest) {
    return "Hello register token. Request: " + tokenRequest.toString();
  }
}
