package softuni.battle_ships_exam.domain.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatsShipModel {
    private String name;

    private Long health;

    private Long power;

    @Override
    public String toString() {
        String stats = "| %s | %s | %s |";

        return String.format(stats, name, health, power);
    }
}
