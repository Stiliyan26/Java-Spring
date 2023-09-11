package com.softuni.pathfinder.domain.beans;

import com.softuni.pathfinder.domain.dto.model.RoleModel;

import java.util.Set;

public class LoggedUser {
    private Long id;

    private String username;

    private Set<RoleModel> roleModels;


    public LoggedUser() {
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
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

    public Set<RoleModel> getRoleModels() {
        return roleModels;
    }

    public LoggedUser setRoleModels(Set<RoleModel> roleModels) {
        this.roleModels = roleModels;
        return this;
    }

    public void clearFields() {
        this.id = null;
        this.username = null;
        this.roleModels = null;
    }
}