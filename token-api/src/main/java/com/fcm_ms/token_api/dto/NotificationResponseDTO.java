package com.fcm_ms.token_api.dto;

import java.util.Map;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class NotificationResponseDTO {

  private LocalDateTime timestamp;
  private String message;
  private Map<String, Integer> notifiedDevicesCounts;
  private HttpStatusDetail httpStatus;

  public HttpStatus _getHttpStatus() {
    return HttpStatus.valueOf(this.httpStatus.getName());
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class HttpStatusDetail {
    private String name;
    private Integer code;
  }

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
      .message("Fired notifications for " + totalMessages + " instances of user " + userExternalId)
      .notifiedDevicesCounts(counts)
      .httpStatus(new HttpStatusDetail(
        httpStatus.name(), httpStatus.value()
      ))
      .build();
  }
}
