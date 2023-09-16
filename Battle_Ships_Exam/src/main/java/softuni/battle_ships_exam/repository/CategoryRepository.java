package softuni.battle_ships_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battle_ships_exam.domain.entities.Category;
import softuni.battle_ships_exam.domain.enums.CategoryType;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(CategoryType name);
}
