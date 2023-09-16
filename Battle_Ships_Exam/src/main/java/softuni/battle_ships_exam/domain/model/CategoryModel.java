package softuni.battle_ships_exam.domain.model;

import lombok.*;
import softuni.battle_ships_exam.domain.enums.CategoryType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel {

    private String id;

    private CategoryType name;

    private String description;
}