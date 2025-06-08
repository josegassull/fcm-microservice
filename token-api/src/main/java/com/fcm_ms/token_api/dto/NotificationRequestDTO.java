package com.fcm_ms.token_api.dto;

import java.util.Map;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationRequestDTO {

  @NotBlank(message = "Notification title is required")
  private String title;

  @NotBlank(message = "Notification body is required")
  private String body;

  private Map<String, String> data;
}
