package softuni.battle_ships_exam.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import softuni.battle_ships_exam.domain.enums.CategoryType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category extends BaseEntity {
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CategoryType name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
