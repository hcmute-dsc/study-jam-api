package com.gdsc.studyjamapi.validation.constraint;

import com.gdsc.studyjamapi.validation.validator.ValidRoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ValidRoleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoleConstraint {
  String message() default "The provided role is invalid.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
