package com.gdsc.studyjamapi.dto.validator;

import com.gdsc.studyjamapi.dto.request.CreateTaskRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public class ValidTimeRangeValidator
    implements ConstraintValidator<ValidTimeRange, CreateTaskRequest> {
  @Override
  public void initialize(ValidTimeRange constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(CreateTaskRequest task, ConstraintValidatorContext context) {
    boolean isValid = true;
    if (!task.getStartTime().isAfter(LocalDateTime.now())) {
      context
          .buildConstraintViolationWithTemplate("Start time is invalid")
          .addPropertyNode("startTime")
          .addConstraintViolation()
          .disableDefaultConstraintViolation();
      isValid = false;
    }
    if (!task.getStartTime().isAfter(task.getEndTime())) {
      context
          .buildConstraintViolationWithTemplate("Start time should be before end time")
          .addPropertyNode("endTime")
          .addConstraintViolation()
          .disableDefaultConstraintViolation();
      isValid = false;
    }
    return isValid;
  }
}
