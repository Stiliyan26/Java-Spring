package com.softuni.pathfinder.web;

import com.softuni.pathfinder.domain.dto.model.RoleModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @PatchMapping("/changeUserPermission/{id}")
    @ResponseBody
    public Set<RoleModel> changeRoles(@PathVariable String id,
                                      @RequestParam(defaultValue = "false")Boolean shouldReplaceCurrentRoles,
                                      @RequestBody String roleName){
        return null;
    }
}
