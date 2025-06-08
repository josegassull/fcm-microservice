package com.fcm_ms.token_api.controller;

import java.util.Optional;
import java.util.List;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.BatchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;

import com.fcm_ms.token_api.dto.BasicNotificationRequestDTO;
import com.fcm_ms.token_api.dto.ErrorResponseDTO;
import com.fcm_ms.token_api.service.UserNotificationService;
import com.fcm_ms.token_api.util.StringToIntUtil;
import com.fcm_ms.token_api.entity.Token;

@RestController
@RequestMapping("api/notify/user")
@RequiredArgsConstructor
public class UserNotificationController {

  private final FirebaseMessaging firebaseMessaging;
  private final UserNotificationService userNotificationService;

  @PostMapping("{user_external_id}")
  public ResponseEntity<?> notifyUser(
      @PathVariable("user_external_id") String userExternalId,
      @Valid @RequestBody BasicNotificationRequestDTO basicNotificationRequestDTO,
      HttpServletRequest request) {

    String response = "[DEBUG]: ";

    Optional<ErrorResponseDTO> existingError = StringToIntUtil.intOrErrorDTO(
      userExternalId,
      "user_external_id",
      request.getRequestURI()
    );

    if (existingError.isPresent())
      return ResponseEntity
        .badRequest()
        .body(existingError.get());

    List<Message> userMessages = this.userNotificationService.getMessages(
      Integer.parseInt(userExternalId), basicNotificationRequestDTO
    );

    /* TODO pass additional data in notification
     * {
     *  "title",
     *  "body",
     *  "data": {
     *    "<key_1>": "<value_1>",
     *    "<key_2>": "<value_2>",
     *    ... }
     * }
     */

    /* TODO response
     *  - count successfully sent messages
     *  - count failure messages
     *  - "Notified {messagesCount} instances for user {user_external_id}"
     */

    for (Message m : userMessages) {
      try {
        response += "\n" + firebaseMessaging.send(m);
      } catch (Exception ex) {
        response += "\n" + ex.getMessage();
      }
    }

    return ResponseEntity.ok().body(response);
  }

}
