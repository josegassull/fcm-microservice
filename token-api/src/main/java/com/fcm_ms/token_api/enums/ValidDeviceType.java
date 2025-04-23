package com.fcm_ms.token_api.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DeviceTypeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDeviceType {

  String message() default "Invalid device type. Allowed values are: 'Android', 'iOS', 'other'";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
