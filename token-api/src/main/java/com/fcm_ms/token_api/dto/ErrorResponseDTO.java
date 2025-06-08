package com.fcm_ms.token_api.dto;

import java.util.Map;
import java.time.LocalDateTime;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ErrorResponseDTO extends BaseHttpResponseDTO {

  private LocalDateTime timestamp;
  private String error;
  private Map<String, String> errors;
  private String path;

  public static ErrorResponseDTO of(
      HttpStatus httpStatus,
      String error,
      Map<String, String> errors,
      String path) {

    return ErrorResponseDTO.builder()
      .timestamp(LocalDateTime.now())
      .error(error)
      .errors(errors)
      .path(path)
      .httpStatus(new HttpStatusDetail(
        httpStatus.name(), httpStatus.value()
      ))
      .build();
    }
}
