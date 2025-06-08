package com.fcm_ms.token_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationRequestDTO {

  @NotBlank(message = "Notification title is required")
  String title;

  @NotBlank(message = "Notification body is required")
  String body;
}
