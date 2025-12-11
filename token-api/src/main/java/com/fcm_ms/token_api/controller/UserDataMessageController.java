package com.fcm_ms.token_api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.RequiredArgsConstructor;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.servlet.http.HttpServletRequest;

import com.fcm_ms.token_api.util.StringToIntUtil;
import com.fcm_ms.token_api.dto.ErrorResponseDTO;

@RestController
@RequestMapping("api/data-message/user")
@RequiredArgsConstructor
public class UserDataMessageController {

  private final FirebaseMessaging firebaseMessaging;

  @PostMapping("{user_external_id}")
  public String dataMessageUser(
    @PathVariable("user_external_id") String userExternalId,
    HttpServletRequest request) {

    Optional<ErrorResponseDTO> existingError = StringToIntUtil.intOrErrorDTO(
      userExternalId,
      "user_external_id",
      request.getRequestURI()
    );

    if (existingError.isPresent())
      return "Error";

    return "HEllo";
  }

}
