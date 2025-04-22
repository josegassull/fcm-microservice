package com.fcm_ms.token_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TokenRequest {

  @NotBlank(message = "FCM Token is required")
  private String token;
  private String deviceUuid;
  private String deviceType;
  private Integer userExternalId;
}
