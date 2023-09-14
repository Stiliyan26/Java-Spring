package com.softuni.mobilele.web;

import com.softuni.mobilele.domain.dtos.banding.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.banding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.view.UserRoleViewModelDto;
import com.softuni.mobilele.domain.entities.Model;
import com.softuni.mobilele.services.role.UserRoleService;
import com.softuni.mobilele.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final String BINDING_RESULT = "org.springframework.validation.BindingResult.";

    private final UserRoleService roleService;
    private final UserService userService;

    @Autowired
    public UserController(UserRoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    //http mappings

    @GetMapping("/register")
    public String getRegister(Model model) {
        return "auth-register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid @ModelAttribute(name = "userRegisterForm") UserRegisterFormDto userRegisterInfo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterForm", userRegisterInfo)
                    .addFlashAttribute(BINDING_RESULT + "userRegisterForm", bindingResult);

            return "redirect:register";
        }

        this.userService.registerUser(userRegisterInfo);

        return "redirect:login";

    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return super.view("auth-login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Valid @ModelAttribute(name = "userLoginForm") UserLoginFormDto userLoginForm,
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("auth-login",
                    modelAndView.addObject("userLoginForm", userLoginForm));
        }

        return super.redirect("/");
    }

    @PostMapping("/logout")
    public ModelAndView postLogout() {
        this.userService.logout();

        return super.redirect("/");
    }

    //Model attributes
    @ModelAttribute(name = "userRegisterForm")
    public UserRegisterFormDto initUserRegisterFormDto() {
        return new UserRegisterFormDto();
    }

    @ModelAttribute(name = "userLoginForm")
    public UserLoginFormDto initUserLoginForm() {
        return new UserLoginFormDto();
    }

    @ModelAttribute(name = "roles")
    public List<UserRoleViewModelDto> getAllRoles() {
        return this.roleService.getAll();
    }
}
