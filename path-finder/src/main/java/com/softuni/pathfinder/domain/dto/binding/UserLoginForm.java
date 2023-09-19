package com.softuni.pathfinder.domain.dto.binding;

import com.softuni.pathfinder.validations.checkUserExistance.ValidateLoginForm;

@ValidateLoginForm
public class UserLoginForm {
    private String username;

    private String password;

    public UserLoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginForm setPassword(String password) {
        this.password = password;
        return this;
    }
}