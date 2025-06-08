package com.fcm_ms.token_api.dto;

import java.util.Map;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {

  private LocalDateTime timestamp;
  private int status;
  private String error;
  private Map<String, String> errors;
  private String path;

  public static ErrorResponseDTO of(
      int status,
      String error,
      Map<String, String> errors,
      String path) {

    return ErrorResponseDTO.builder()
      .timestamp(LocalDateTime.now())
      .status(status)
      .error(error)
      .errors(errors)
      .path(path)
      .build();
    }
}
