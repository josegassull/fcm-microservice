package com.fcm_ms.token_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("api")
public class TokenController {

  @PostMapping("register")
  public String registerNewToken() {
    return "Hello register token";
  }
}
