package com.fcm_ms.token_api.enums;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PathIdValidator implements ConstraintValidator<ValidPathId, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException nfx) {
      return false;
    }
    return value != null && !value.trim().isEmpty();
  }
}
