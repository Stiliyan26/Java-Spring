package com.softuni.mobilele.validations.userExists;

import com.softuni.mobilele.domain.dtos.banding.UserLoginFormDto;
import com.softuni.mobilele.domain.entities.User;
import com.softuni.mobilele.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public record LoginUserValidator(UserRepository userRepository, ModelMapper modelMapper)
        implements ConstraintValidator<ValidateLoginUser, UserLoginFormDto> {

    @Override
    public void initialize(ValidateLoginUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginFormDto userLogin,
                           ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> loggingCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        return loggingCandidate.isPresent()
                && loggingCandidate.get()
                .getPassword()
                .equals(userLogin.getPassword());
    }
}
