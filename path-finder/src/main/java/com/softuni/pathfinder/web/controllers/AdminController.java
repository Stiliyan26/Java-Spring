package com.softuni.pathfinder.web.controllers;

import com.softuni.pathfinder.domain.dto.binding.RoleChangeForm;
import com.softuni.pathfinder.domain.enums.RoleName;
import com.softuni.pathfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @PatchMapping("/changeUserPermission/{id}")
    @ResponseBody
    public Set<RoleName> changeRoles(@PathVariable Long id,
                                     @RequestParam(defaultValue = "false")Boolean shouldReplaceCurrentRoles,
                                     @RequestBody RoleChangeForm roleName) {
        this.userService.changeUserPermissions(id, roleName, shouldReplaceCurrentRoles);

        return Set.of(RoleName.ADMIN, RoleName.MODERATOR, RoleName.USER);
    }
}
