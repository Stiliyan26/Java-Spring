package softuni.battle_ships_exam.validations.passwordMatcher;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import softuni.battle_ships_exam.domain.model.binding.UserRegisterModel;

public class PasswordMatcher implements ConstraintValidator<PasswordMatch, UserRegisterModel> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterModel userRegisterModel, ConstraintValidatorContext context) {
        if (userRegisterModel.getPassword() != null
                && userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(userRegisterModel.getConfirmPassword())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
