package com.softuni.pathfinder.web.controllers;

import com.softuni.pathfinder.domain.dto.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dto.binding.UserRegisterForm;
import com.softuni.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public ModelAndView getRegister(ModelAndView modelAndView) {
        return super.view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(ModelAndView modelAndView,
                                     @Validated UserRegisterForm userRegisterForm,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("register", modelAndView
                    .addObject("userRegisterForm", userRegisterForm));
        }

        this.userService.registerUser(userRegisterForm);

        return super.redirect("login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return super.view("/login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(
            @Validated UserLoginForm userLoginForm,
            BindingResult bindingResult,
            ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            return super.view("login",
                    modelAndView.addObject("userLoginForm", userLoginForm));
        }

        return this.userService.loginUser(userLoginForm).isValid()
                ? super.redirect("/")
                : super.redirect("login");
    }

    @GetMapping("/logout")
    public ModelAndView postLogout(){
        this.userService.logout();
        return super.redirect("/");
    }

    @ModelAttribute(name = "userRegisterForm")
    public UserRegisterForm initUserRegisterForm() {
        return new UserRegisterForm();
    }


    @ModelAttribute(name = "userLoginForm")
    public UserLoginForm initUserLoginForm() {
        return new UserLoginForm();
    }
}
