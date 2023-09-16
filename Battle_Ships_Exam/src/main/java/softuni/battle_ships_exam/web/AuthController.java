package softuni.battle_ships_exam.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.battle_ships_exam.domain.model.binding.UserLoginModel;
import softuni.battle_ships_exam.domain.model.binding.UserRegisterModel;
import softuni.battle_ships_exam.services.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final String BINDING_RESULT = "org.springframework.validation.BindingResult.";

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(
            @Valid @ModelAttribute(name = "userRegisterModel") UserRegisterModel userRegisterModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterModel", userRegisterModel)
                    .addFlashAttribute(BINDING_RESULT + "userRegisterModel", bindingResult);

            return "redirect:register";
        }

        this.authService.registerUser(userRegisterModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(
            @Valid @ModelAttribute(name = "userLoginModel") UserLoginModel userLoginModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginModel", userLoginModel)
                    .addFlashAttribute(BINDING_RESULT + "userLoginModel", bindingResult);

            return "redirect:login";
        }

        this.authService.loginUser(userLoginModel.getUsername());

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String getLogout(){
        this.authService.logout();

        return "redirect:/";
    }

    //Model attributes
    @ModelAttribute(name = "userRegisterModel")
    public UserRegisterModel initUserRegisterModel() {
        return new UserRegisterModel();
    }

    @ModelAttribute(name = "userLoginModel")
    public UserLoginModel initUserLoginModel() {
        return new UserLoginModel();
    }
}
