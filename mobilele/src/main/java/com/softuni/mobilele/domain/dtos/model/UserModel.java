package com.softuni.mobilele.domain.dtos.model;

import java.util.Date;
import java.util.List;

public class UserModel {
    private String id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Boolean isActive;

    private List<UserRoleDto> roles;

    private Date imageUrl;

    private Date created;

    private Date modified;

    public UserModel() {
    }

    public boolean IsValid() {
        return this.id != null;
    }

    public String getId() {
        return id;
    }

    public UserModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserModel setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public List<UserRoleDto> getRoles() {
        return roles;
    }

    public UserModel setRoles(List<UserRoleDto> roles) {
        this.roles = roles;
        return this;
    }

    public Date getImageUrl() {
        return imageUrl;
    }

    public UserModel setImageUrl(Date imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public UserModel setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getModified() {
        return modified;
    }

    public UserModel setModified(Date modified) {
        this.modified = modified;
        return this;
    }
}
