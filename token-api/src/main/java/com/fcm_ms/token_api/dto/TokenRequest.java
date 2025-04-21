package com.fcm_ms.token_api.dto;

import lombok.Data;

@Data
public class TokenRequest {

  private String token;
  private String userExternalId;
  private String deviceUuid;
  private String deviceType;
}
