package softuni.battle_ships_exam.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.battle_ships_exam.domain.entities.User;
import softuni.battle_ships_exam.domain.helpers.LoggedUser;
import softuni.battle_ships_exam.domain.model.binding.UserLoginModel;
import softuni.battle_ships_exam.domain.model.binding.UserRegisterModel;
import softuni.battle_ships_exam.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            ModelMapper modelMapper,
            LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void registerUser(UserRegisterModel userRegisterModel) {
        this.userRepository.saveAndFlush(this.modelMapper
                .map(userRegisterModel, User.class));
    }

    public void loginUser(String username) {
        User user = this.userRepository
                .findByUsername(username)
                .get();

        this.loggedUser.setId(user.getId());
    }

    public void logout() {
        this.loggedUser.clearUser();
    }
}
