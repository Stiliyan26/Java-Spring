package softuni.battle_ships_exam.validations.checkUserExistance;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.battle_ships_exam.domain.model.UserModel;
import softuni.battle_ships_exam.domain.model.binding.UserLoginModel;
import softuni.battle_ships_exam.services.UserService;

public class UserLoginValidator implements ConstraintValidator<ValidateLoginForm, UserLoginModel> {
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
    public boolean isValid(UserLoginModel userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        UserModel loggingCandidate = this.userService.findByUsername(userLoginModel.getUsername());

        return  loggingCandidate.getId() != null
                && loggingCandidate.getPassword().equals(userLoginModel.getPassword());
    }
}
