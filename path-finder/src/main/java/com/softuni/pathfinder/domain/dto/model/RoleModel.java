package com.softuni.pathfinder.domain.dto.model;

import com.softuni.pathfinder.domain.enums.RoleName;

public class RoleModel {
    private RoleName role;

    public RoleModel() {
    }

    public RoleName getRole() {
        return role;
    }

    public RoleModel setRole(RoleName role) {
        this.role = role;
        return this;
    }
}
