package com.fcm_ms.token_api.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.RequiredArgsConstructor;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.BatchResponse;

import com.fcm_ms.token_api.util.StringToIntUtil;
import com.fcm_ms.token_api.dto.ErrorResponseDTO;
import com.fcm_ms.token_api.dto.DataMessageRequestDTO;
import com.fcm_ms.token_api.dto.NotificationResponseDTO;
import com.fcm_ms.token_api.service.UserDataMessageService;

@RestController
@RequestMapping("api/data-message/user")
@RequiredArgsConstructor
public class UserDataMessageController {

  private final FirebaseMessaging firebaseMessaging;
  private final UserDataMessageService userDataMessageService;

  @PostMapping("{user_external_id}")
  public ResponseEntity<?> dataMessageUser(
    @PathVariable("user_external_id") String userExternalId,
    @Valid @RequestBody DataMessageRequestDTO dataMessageRequestDTO,
    HttpServletRequest request) {

    Optional<ErrorResponseDTO> existingError = StringToIntUtil.intOrErrorDTO(
      userExternalId,
      "user_external_id",
      request.getRequestURI()
    );

    if (existingError.isPresent())
      return new ResponseEntity<>(
        existingError.get(),
        HttpStatus.BAD_REQUEST
      );

    MulticastMessage message = this.userDataMessageService.getMulticastDataMessage(
      Integer.parseInt(userExternalId), dataMessageRequestDTO
    );

    int success = 0;
    int failure = 0;
    int total = 0;
    try {
      BatchResponse response = this.firebaseMessaging.sendEachForMulticast(message);
      success = response.getSuccessCount();
      failure = response.getFailureCount();
      total = response.getResponses().size();
    } catch (Exception ex) {
      ex.printStackTrace();
      return new ResponseEntity<>(
        "There was an error sending the data message to the user",
        HttpStatus.INTERNAL_SERVER_ERROR
      );
    }

    NotificationResponseDTO notifResponse = NotificationResponseDTO.of(
      total,
      Integer.parseInt(userExternalId),
      success, failure
    );

    return new ResponseEntity<>(
      notifResponse,
      notifResponse._getHttpStatus()
    );
  }

}
