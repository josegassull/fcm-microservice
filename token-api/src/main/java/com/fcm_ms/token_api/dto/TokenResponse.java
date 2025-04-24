package com.fcm_ms.token_api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import com.fcm_ms.token_api.entity.Token;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

  private String message;
  private String description;
  private String token;
  private Integer userExternalId;
  private Device device;
  private HttpStatusDetail httpStatus;

  public HttpStatus _getHttpStatus() {
    return HttpStatus.valueOf(this.httpStatus.getName());
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Device {

    private String uuid;
    private String type;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class HttpStatusDetail {

    private String name;
    private Integer code;
  }

  public static TokenResponse of(
      Boolean isNewToken,
      Token token,
      Integer userExternalId,
      com.fcm_ms.token_api.entity.Device device) {

    String message = (isNewToken ? "Created" : "Updated existing") + " token";
    String description = "Successfully " + message.toLowerCase()
      + " for user with external_id '"
      + userExternalId + "' and device with uuid '" + device.getUuid() + "'";
    HttpStatus httpStatus = isNewToken ? HttpStatus.CREATED : HttpStatus.OK;

    return TokenResponse.builder()
      .token(token.getToken())
      .message(message)
      .description(description)
      .userExternalId(userExternalId)
      .device(new TokenResponse.Device(
        device.getUuid(), device.getType().name()
      ))
      .httpStatus(new TokenResponse.HttpStatusDetail(
        httpStatus.name(), httpStatus.value()
      ))
      .build();
  }
}
