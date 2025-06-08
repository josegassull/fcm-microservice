package com.fcm_ms.token_api.util;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import org.springframework.http.HttpStatus;

import com.fcm_ms.token_api.dto.ErrorResponseDTO;

public class StringToIntUtil {

  public static boolean isValidInteger(String str) {
    try {
      if (str.trim().isEmpty())
        return false;
      Integer.parseInt(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static Optional<ErrorResponseDTO> intOrErrorDTO(
    String str,
    String stringName,
    String path) {

    if (StringToIntUtil.isValidInteger(str))
      return Optional.empty();

    Map<String, String> errors = new HashMap<>();
    errors.put("Invalid format", stringName + " must be a number");

    return Optional.of(ErrorResponseDTO.of(
      HttpStatus.BAD_REQUEST.value(),
      "Invalid " + stringName,
      errors,
      path
    ));
  }
}
