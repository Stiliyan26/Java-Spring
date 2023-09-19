package com.softuni.pathfinder.service;

import com.softuni.pathfinder.domain.beans.LoggedUser;
import com.softuni.pathfinder.domain.dto.binding.RoleChangeForm;
import com.softuni.pathfinder.domain.dto.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dto.binding.UserRegisterForm;
import com.softuni.pathfinder.domain.dto.model.UserModel;
import com.softuni.pathfinder.domain.dto.view.UserProfileModel;
import com.softuni.pathfinder.domain.entities.Role;
import com.softuni.pathfinder.domain.entities.User;
import com.softuni.pathfinder.domain.enums.Level;
import com.softuni.pathfinder.domain.enums.RoleName;
import com.softuni.pathfinder.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        userToSave.setLevel(Level.BEGINNER);

        this.userRepository.saveAndFlush(userToSave);
    }

    public UserModel loginUser(UserLoginForm userLogin) {
        Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        UserModel userConfirmation = loginCandidate.isPresent()
                && loginCandidate.get().getPassword().equals(userLogin.getPassword())
                    ? this.modelMapper.map(loginCandidate.get(), UserModel.class)
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

    public Set<RoleName> changeUserPermissions(Long userId,
                                               RoleChangeForm roleNameToSet,
                                               boolean shouldReplaceCurrentRoles) {
        final User user = this.userRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new);

        final Role roleToAdd = this.modelMapper
                .map(this.roleService.findRoleByName(roleNameToSet.getRoleName()), Role.class);

        if (shouldReplaceCurrentRoles) {
            user.setRoles(Set.of(roleToAdd));
        } else {
            user.addRole(roleToAdd);
        }

        this.userRepository.saveAndFlush(user);

        return user.getRoles()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toSet());
    }

    public UserModel findByUsername(String username) {
        return this.modelMapper
                .map(this.userRepository
                        .findByUsername(username)
                        .orElse(new User()), UserModel.class);
    }

    public UserProfileModel getLoggedUserProfile() {
        return this.modelMapper
                .map(this.userRepository.findById(loggedUser.getId())
                        .orElseThrow(NoSuchElementException::new),
                        UserProfileModel.class);
    }
}
