package softuni.battle_ships_exam.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ships")
public class Ship extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long health;

    @Column(nullable = false)
    private Long power;

    @Column(nullable = false)
    private Date created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
