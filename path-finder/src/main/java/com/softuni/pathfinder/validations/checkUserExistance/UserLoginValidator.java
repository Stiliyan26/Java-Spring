package com.softuni.pathfinder.validations.checkUserExistance;

import com.softuni.pathfinder.domain.dto.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dto.model.UserModel;
import com.softuni.pathfinder.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginValidator implements ConstraintValidator<ValidateLoginForm, UserLoginForm> {
    private final UserService userService;

    @Autowired
    public UserLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ValidateLoginForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginForm userLoginForm, ConstraintValidatorContext constraintValidatorContext) {
        UserModel loggingCandidate = this.userService
                .findByUsername(userLoginForm.getUsername());

        return loggingCandidate.isValid()
                && loggingCandidate.getPassword()
                    .equals(userLoginForm.getPassword());
    }
}
