package com.fcm_ms.token_api.dto;

import java.util.Map;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DataMessageRequestDTO {

  @NotEmpty(message = "Data is required")
  private Map<@NotBlank(message = "Data Key cannot be empty") String, String> data;
}
