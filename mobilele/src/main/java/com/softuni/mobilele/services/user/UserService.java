package com.softuni.mobilele.services.user;

import com.softuni.mobilele.domain.dtos.banding.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.banding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.model.UserModel;

public interface UserService {
    UserModel registerUser(UserRegisterFormDto userRegister);

    UserModel loginUser(UserLoginFormDto userLogin);
}