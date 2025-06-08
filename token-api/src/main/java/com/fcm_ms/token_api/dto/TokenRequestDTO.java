package com.fcm_ms.token_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.fcm_ms.token_api.enums.ValidDeviceType;

@Data
public class TokenRequestDTO {

  @NotBlank(message = "FCM Token is required")
  private String token;

  @NotBlank(message = "Device UUID is required")
  private String deviceUuid;

  @NotBlank(message = "Device type is required")
  @ValidDeviceType
  private String deviceType;

  @NotNull(message = "User ID is required")
  private Integer userExternalId;
}
