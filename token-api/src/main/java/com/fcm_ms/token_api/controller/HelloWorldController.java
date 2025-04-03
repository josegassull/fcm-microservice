package com.fcm_ms.token_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("hello-world")
public class HelloWorldController {

  @GetMapping
  public String get() {
    return "Hello world!";
  }
}
