package com.fcm_ms.token_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api")
public class HelloWorldController {

  @GetMapping("hello-world")
  public String get() {
    return "Hello JARVIS!";
  }
}
