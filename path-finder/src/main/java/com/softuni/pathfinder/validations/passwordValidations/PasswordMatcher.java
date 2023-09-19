package com.softuni.pathfinder.validations.passwordValidations;

import com.softuni.pathfinder.domain.dto.binding.UserRegisterForm;
import com.softuni.pathfinder.validations.passwordValidations.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterForm> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterForm userRegisterForm, ConstraintValidatorContext context) {
        if (userRegisterForm.getPassword() != null
            && userRegisterForm.getPassword().equals(userRegisterForm.getConfirmPassword())){
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("confirmPassword")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
