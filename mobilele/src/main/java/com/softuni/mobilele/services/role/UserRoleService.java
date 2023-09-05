package com.softuni.mobilele.services.role;

import com.softuni.mobilele.domain.dtos.view.UserRoleViewModelDto;
import com.softuni.mobilele.services.init.DataBaseInitService;

import java.util.List;

public interface UserRoleService extends DataBaseInitService {
    List<UserRoleViewModelDto> getAll();
}