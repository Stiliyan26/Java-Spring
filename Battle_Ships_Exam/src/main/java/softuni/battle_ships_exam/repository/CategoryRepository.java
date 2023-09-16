package softuni.battle_ships_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battle_ships_exam.domain.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
