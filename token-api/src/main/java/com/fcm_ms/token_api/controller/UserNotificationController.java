package com.fcm_ms.token_api.controller;

import java.util.Optional;
import java.util.List;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.BatchResponse;
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
  public String notifyUser(
      @PathVariable("user_external_id") String userExternalId,
      @Valid @RequestBody BasicNotificationRequestDTO basicNotificationRequestDTO,
      HttpServletRequest request) {

    String response = "Notification not sent";

    Optional<ErrorResponseDTO> existingError = StringToIntUtil.intOrErrorDTO(
      userExternalId,
      "user_external_id",
      request.getRequestURI()
    );

    if (existingError.isPresent())
      return existingError.get().toString();

    /* TODO
     *  - get list of tokens of user with externalId X
     *  - create Notification with hardcoded title and body
     *  - send to all tokens
     * */

    List<Message> userMessages = this.userNotificationService.getMessages(
      Integer.parseInt(userExternalId), basicNotificationRequestDTO
    );

    /*
    if (userMessages.isEmpty())
      return "No tokens found for this user";

      for (int i = 0; i < userMessages.size(); i++) {
        try {
            response = firebaseMessaging.send(userMessages.get(i));
            System.out.println("Message " + i + " sent successfully: " + response);
        } catch (Exception e) {
        e.printStackTrace();
            System.err.println("Message " + i + " failed: " + e.getMessage());
        }
      }
    return response;
    */

    try {
      BatchResponse batchResponse = firebaseMessaging.sendAll(userMessages);

      return String.format(
        "Sent %d messages: %d succeeded, %d failed",
        userMessages.size(),
        batchResponse.getSuccessCount(),
        batchResponse.getFailureCount()
      );
    } catch (Exception ex) {
      return "Failed to send notifications: " + ex.getMessage();
    }
  }

}
