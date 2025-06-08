package com.fcm_ms.token_api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.entity.Device;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {

  private String message;
  private String description;
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

  public static TokenResponseDTO of(
      Boolean isNewToken,
      Token token,
      Integer userExternalId,
      Device device) {

    String message = (isNewToken ? "Created" : "Updated existing") + " token";
    String description = "Successfully " + message.toLowerCase()
      + " for user with external_id '"
      + userExternalId + "' and device with uuid '" + device.getUuid() + "'";
    HttpStatus httpStatus = isNewToken ? HttpStatus.CREATED : HttpStatus.OK;

    return TokenResponseDTO.builder()
      .message(message)
      .description(description)
      .httpStatus(new TokenResponseDTO.HttpStatusDetail(
        httpStatus.name(), httpStatus.value()
      ))
      .build();
  }
}
