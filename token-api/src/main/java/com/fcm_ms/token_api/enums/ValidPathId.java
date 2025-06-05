package com.fcm_ms.token_api.enums;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PathIdValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPathId {

  String message() default "Invalid ID format";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
