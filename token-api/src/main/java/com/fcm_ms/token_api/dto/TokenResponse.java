package com.fcm_ms.token_api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

  // public static TokenResponse of(
  //     Boolean isNew,
  //     String token,
  //     Integer userExternalId,
  //     com.fcm_ms.token_api.entity.Device device) {
  //
  //   String message = isNew ? "created" : "updated";
  //   String description = isNew ? "desC new" : "desC upd";
  //   HttpStatus httpStatus = isNew ? HttpStatus.CREATED : HttpStatus.OK;
  //
  //   return TokenResponse.builder()
  //     .message(message)
  //     .description(description)
  //     .userExternalId(userExternalId)
  //     .device(new TokenResponse.Device(device.getUuid, device.getType()))
  //     .httpStatus(new TokenResponse.HttpStatusDetail(httpStatus.name(), httpStatus.value()))
  //     .build();
  // }
}
