package com.example.java_spring_fund_lab_01.models.validation;

import com.example.java_spring_fund_lab_01.services.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

  private final UserService userService;

  public UniqueUserNameValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(String userName, ConstraintValidatorContext context) {
    if (userName == null) {
      return true;
    }
    return userService.isUserNameFree(userName);
  }
}
