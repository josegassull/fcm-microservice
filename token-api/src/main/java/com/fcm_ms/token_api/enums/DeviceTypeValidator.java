package com.fcm_ms.token_api.enums;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DeviceTypeValidator implements ConstraintValidator<ValidDeviceType, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    switch (value) {
      case "Android":
      case "iOS":
      case "other":
        return true;
      default:
        return false;
    }
  }
}
