package com.softuni.pathfinder.service;

import com.softuni.pathfinder.domain.beans.LoggedUser;
import com.softuni.pathfinder.domain.dto.banding.UserLoginForm;
import com.softuni.pathfinder.domain.dto.banding.UserRegisterForm;
import com.softuni.pathfinder.domain.dto.model.UserModel;
import com.softuni.pathfinder.domain.entities.User;
import com.softuni.pathfinder.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       ModelMapper modelMapper,
                       LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void registerUser(UserRegisterForm userRegister) {
        final UserModel userModel = this.modelMapper.map(userRegister, UserModel.class);

        userModel.setRoles(this.userRepository.count() == 0
                ? this.roleService.findAllRoles()
                : Set.of(this.roleService.findRoleByName("USER")));

        final User userToSave = this.modelMapper.map(userModel, User.class);

        this.userRepository.saveAndFlush(userToSave);
    }

    public UserModel loginUser(UserLoginForm userLogin) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        UserModel userConfirmation = loginCandidate.isPresent()
                && loginCandidate.get().getPassword().equals(userLogin.getPassword())
                ? this.modelMapper.map(loginCandidate, UserModel.class)
                : new UserModel();

        if (userConfirmation.isValid()) {
            loggedUser
                    .setId(userConfirmation.getId())
                    .setUsername(userConfirmation.getUsername())
                    .setRoleModels(userConfirmation.getRoles());
        }

        return userConfirmation;
    }

    public void logout() {
        this.loggedUser.clearFields();
    }

}
