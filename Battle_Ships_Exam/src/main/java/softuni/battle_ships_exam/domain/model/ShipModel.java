package softuni.battle_ships_exam.domain.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipModel {
    private String id;

    private String name;

    private Long health;

    private Long power;

    private Date created;

    private CategoryModel category;

    private UserModel user;
}