package com.fcm_ms.token_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TokenRequest {

  @NotBlank(message = "FCM Token is required")
  private String token;

  @NotBlank(message = "Device UUID is required")
  private String deviceUuid;

  @NotBlank(message = "Device type is required")
  private String deviceType;

  @NotNull(message = "A user ID is required")
  private Integer userExternalId;
}
