package com.fcm_ms.token_api.dto;

import java.util.Map;
import java.time.LocalDateTime;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@SuperBuilder
public class NotificationResponseDTO extends BaseHttpResponseDTO {

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

    HttpStatus httpStatus = failureDevices == 0 ?
      HttpStatus.OK :
      HttpStatus.PARTIAL_CONTENT;

    return NotificationResponseDTO.builder()
      .timestamp(LocalDateTime.now())
      .message("Fired notifications for '" + totalMessages + "' instances of user with external_id '" + userExternalId + "'")
      .notifiedDevicesCounts(counts)
      .httpStatus(new HttpStatusDetail(
        httpStatus.name(), httpStatus.value()
      ))
      .build();
  }
}
