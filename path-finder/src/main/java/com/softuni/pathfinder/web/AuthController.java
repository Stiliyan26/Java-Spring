package com.softuni.pathfinder.web;

import com.softuni.pathfinder.domain.dto.banding.UserLoginForm;
import com.softuni.pathfinder.domain.dto.banding.UserRegisterForm;
import com.softuni.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        return super.view("register");
    }

    @PostMapping("/register")
    public ModelAndView postRegister(
            @ModelAttribute UserRegisterForm userRegisterInfo) {
        this.userService.registerUser(userRegisterInfo);

        return super.redirect("/login");
    }

    @GetMapping
    public ModelAndView getLogin() {
        return super.view("/login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(UserLoginForm userLoginForm) {
        return this.userService.loginUser(userLoginForm).isValid()
                ? super.redirect("/")
                : super.redirect("/login");
    }

    @GetMapping("/logout")
    public ModelAndView postLogout(){
        this.userService.logout();
        return super.redirect("/");
    }
}
