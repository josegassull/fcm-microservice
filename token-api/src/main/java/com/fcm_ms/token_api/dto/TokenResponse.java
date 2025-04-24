package com.fcm_ms.token_api.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
}
