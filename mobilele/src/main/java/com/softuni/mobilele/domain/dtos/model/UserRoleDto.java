package com.softuni.mobilele.domain.dtos.model;

public class UserRoleDto {
    private String id;

    private String role;

    public String getId() {
        return id;
    }

    public UserRoleDto() {
    }

    public UserRoleDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRoleDto setRole(String role) {
        this.role = role;
        return this;
    }
}
