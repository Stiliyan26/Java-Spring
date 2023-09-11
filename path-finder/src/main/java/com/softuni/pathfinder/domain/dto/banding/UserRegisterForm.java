package com.softuni.pathfinder.domain.dto.banding;

public class UserRegisterForm {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    public String getUsername() {
        return username;
    }

    public UserRegisterForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterForm setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
