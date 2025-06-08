package com.fcm_ms.token_api.dto;

import java.util.Map;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponseDTO {

  private LocalDateTime timestamp;
  private String message;
  private Map<String, Integer> notifiedDevicesCounts;

  public static NotificationResponseDTO of(
    int totalMessages,
    int userExternalId,
    int successDevices,
    int failureDevices) {

    Map<String, Integer> counts = Map.of(
      "success", successDevices,
      "failure", failureDevices
    );

    return NotificationResponseDTO.builder()
      .timestamp(LocalDateTime.now())
      .message("Fired notifications for " + totalMessages + " instances of user " + userExternalId)
      .notifiedDevicesCounts(counts)
      .build();
  }
}
