package softuni.battle_ships_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.battle_ships_exam.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

