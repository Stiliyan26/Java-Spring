package com.softuni.mobilele.services.user;

import com.softuni.mobilele.domain.beans.LoggedUser;
import com.softuni.mobilele.domain.dtos.banding.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.banding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.model.UserModel;
import com.softuni.mobilele.domain.entities.User;
import com.softuni.mobilele.repositories.UserRepository;
import com.softuni.mobilele.services.init.DataBaseInitService;
import com.softuni.mobilele.services.role.UserRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserRoleService userRoleService,
                           ModelMapper modelMapper,
                           LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @Override
    public void dbInit() {
    }

    @Override
    public boolean isDbInit() {
        return this.userRepository.count() > 0;
    }

    @Override
    public UserModel registerUser(UserRegisterFormDto userRegister) {
        final UserModel userModel = this.modelMapper
                .map(userRegister, UserModel.class);

        userModel.setRoles(this.userRepository.count() == 0
                ? this.userRoleService.findAllRoles()
                : List.of(this.userRoleService.findRoleByName("USER")));

        final User userToSave = this.modelMapper.map(userModel, User.class);

        User user = this.userRepository.saveAndFlush(userToSave);

        return this.modelMapper.map(user, UserModel.class);
    }

    @Override
    public void loginUser(UserLoginFormDto userLogin) {
        UserModel loggingCandidate = this.modelMapper.map(
                this.userRepository.findByUsername(userLogin.getUsername()).get(),
                UserModel.class);

        this.loggedUser
                .setId(loggingCandidate.getId())
                .setUsername(loggingCandidate.getUsername())
                .setRoleModel(loggingCandidate.getRoles());
    }

    @Override
    public void logout() {
        this.loggedUser.clearFields();
    }
}
    //
    //public UserModel loginUser(UserLoginForm userLogin) {
    //    Optional<User> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());
    //
    //    UserModel userConfirmation = loginCandidate.isPresent()
    //            && loginCandidate.get().getPassword().equals(userLogin.getPassword())
    //            ? this.modelMapper.map(loginCandidate, UserModel.class)
    //            : new UserModel();
    //
    //    if (userConfirmation.isValid()) {
    //        loggedUser
    //                .setId(userConfirmation.getId())
    //                .setUsername(userConfirmation.getUsername())
    //                .setRoleModels(userConfirmation.getRoles());
    //    }
    //
    //    return userConfirmation;
    //}