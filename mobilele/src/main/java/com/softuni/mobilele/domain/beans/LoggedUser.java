package com.softuni.mobilele.domain.beans;

import com.softuni.mobilele.domain.dtos.model.UserRoleDto;

import java.util.List;

public class LoggedUser {
    private String id;

    private String username;

    private List<UserRoleDto> roleModel;

    public LoggedUser() {
    }

    public String getId() {
        return id;
    }

    public LoggedUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleDto> getRoleModel() {
        return roleModel;
    }

    public LoggedUser setRoleModel(List<UserRoleDto> roleModel) {
        this.roleModel = roleModel;
        return this;
    }
}
