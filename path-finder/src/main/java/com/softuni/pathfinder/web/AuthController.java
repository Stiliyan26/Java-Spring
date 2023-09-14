package com.softuni.pathfinder.web;

import com.softuni.pathfinder.domain.dto.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dto.binding.UserRegisterForm;
import com.softuni.pathfinder.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView,
                                    @ModelAttribute UserRegisterForm userRegisterForm) {

        modelAndView.addObject("userRegisterInfo", userRegisterForm);

        return super.view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(ModelAndView modelAndView,
                                     @Valid UserRegisterForm userRegisterForm,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute(
                    "org.springframework.validation.BindingResult.userRegisterInfo",
                            bindingResult)
                    .addFlashAttribute("userRegisterInfo", userRegisterForm);

            return super.redirect("register");
        }

        this.userService.registerUser(userRegisterForm);

        return super.redirect("login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return super.view("/login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(UserLoginForm userLoginForm) {
        return this.userService.loginUser(userLoginForm).isValid()
                ? super.redirect("/")
                : super.redirect("login");
    }

    @GetMapping("/logout")
    public ModelAndView postLogout(){
        this.userService.logout();
        return super.redirect("/");
    }
}
