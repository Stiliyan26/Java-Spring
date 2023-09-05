package com.softuni.mobilele.domain.dtos.view;

import com.softuni.mobilele.domain.enums.Role;

public class UserRoleViewModelDto {
    private Role role;

    public Role getRole() {
        return role;
    }

    public UserRoleViewModelDto setRole(Role role) {
        this.role = role;
        return this;
    }
}
