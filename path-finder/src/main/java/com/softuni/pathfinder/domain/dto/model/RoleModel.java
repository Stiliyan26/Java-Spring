package com.softuni.pathfinder.domain.dto.model;

import com.softuni.pathfinder.domain.enums.RoleName;

public class RoleModel {
    private Long id;

    private RoleName roleName;

    public RoleModel() {
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public RoleModel setRoleName(RoleName roleName) {
        this.roleName = roleName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RoleModel setId(Long id) {
        this.id = id;
        return this;
    }
}
