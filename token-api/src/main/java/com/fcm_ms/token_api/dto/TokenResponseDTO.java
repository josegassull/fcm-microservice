package com.fcm_ms.token_api.dto;

import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import com.fcm_ms.token_api.entity.Token;
import com.fcm_ms.token_api.entity.Device;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO extends BaseHttpResponseDTO {

  private String message;
  private String description;

  public static TokenResponseDTO of(
      Boolean isNewToken,
      Token token,
      Integer userExternalId,
      Device device) {

    String message = (isNewToken ? "Created" : "Updated existing") + " token";
    String description = "Successfully " + message.toLowerCase()
      + " for user with external_id '"
      + userExternalId + "' and device with uuid '" + device.getUuid() + "'";
    HttpStatus httpStatus = isNewToken ? HttpStatus.CREATED : HttpStatus.OK;

    return TokenResponseDTO.builder()
      .message(message)
      .description(description)
      .httpStatus(new HttpStatusDetail(
        httpStatus.name(), httpStatus.value()
      ))
      .build();
  }
}
