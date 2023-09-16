package softuni.battle_ships_exam.domain.model.binding;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import softuni.battle_ships_exam.domain.enums.CategoryType;
import softuni.battle_ships_exam.validations.checkShipExistence.ValidateExistenceOfShip;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipAddModel {
    @NotNull
    @Size(min = 2, max = 10)
    @ValidateExistenceOfShip
    private String name;

    @NotNull
    @Positive
    private Long health;

    @NotNull
    @Positive
    private Long power;

    @NotNull
    @PastOrPresent
    private Date created;

    @NotNull
    private CategoryType category;
}
