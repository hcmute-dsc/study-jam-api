package com.gdsc.studyjamapi.validation.validator;

import com.gdsc.studyjamapi.common.Role;
import com.gdsc.studyjamapi.validation.constraint.ValidRoleConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValidRoleValidator implements ConstraintValidator<ValidRoleConstraint, String> {
  @Override
  public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
    return Arrays.stream(Role.values()).map(Enum::name).toList().contains(role);
  }
}
