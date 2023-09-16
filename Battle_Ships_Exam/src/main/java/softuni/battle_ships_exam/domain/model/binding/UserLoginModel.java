package softuni.battle_ships_exam.domain.model.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.battle_ships_exam.validations.checkUserExistance.ValidateLoginForm;

@Getter
@Setter
@NoArgsConstructor
@ValidateLoginForm
public class UserLoginModel {
    @NotNull
    @Size(min = 3, max = 10)
    private String username;

    @NotNull
    @Size(min = 3)
    private String password;
}
