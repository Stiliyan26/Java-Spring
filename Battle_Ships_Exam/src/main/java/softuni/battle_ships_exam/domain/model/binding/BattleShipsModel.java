package softuni.battle_ships_exam.domain.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BattleShipsModel {
    private String loggedUserShip;

    private String notLoggedUserShip;
}
