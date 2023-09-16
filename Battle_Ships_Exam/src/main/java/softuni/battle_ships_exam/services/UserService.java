package softuni.battle_ships_exam.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.battle_ships_exam.domain.entities.User;
import softuni.battle_ships_exam.domain.helpers.LoggedUser;
import softuni.battle_ships_exam.domain.model.UserModel;
import softuni.battle_ships_exam.domain.model.UserWithShipsModel;
import softuni.battle_ships_exam.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    public UserService(
            UserRepository userRepository,
            ModelMapper modelMapper,
            LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public UserModel findByUsername(String username) {
        return this.modelMapper
                .map(this.userRepository.findByUsername(username)
                        .orElse(new User()), UserModel.class);
    }

    public UserModel findById(String id) {
        return this.modelMapper
                .map(this.userRepository.findById(id)
                        .orElse(new User()), UserModel.class);
    }

    public UserModel findByIdNot(String id) {
        return this.modelMapper
                .map(this.userRepository.findByIdNot(id)
                        .orElse(new User()), UserModel.class);
    }
}
