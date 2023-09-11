package com.softuni.mobilele.demo;

import com.softuni.mobilele.domain.dtos.banding.UserRegisterFormDto;
import com.softuni.mobilele.web.BaseController;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.nimbus.State;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/demo")
public class StateController extends BaseController {
    private static final String STATE_USERNAME_KEY = "username";

    @GetMapping("/loginTestMe")
    public ModelAndView getLogin(ModelAndView modelAndView,
                                 @CookieValue(name = STATE_USERNAME_KEY,
                                         defaultValue = "") String username) {
        modelAndView.addObject(STATE_USERNAME_KEY, username);

        return super.view("/demo/login", modelAndView);
    }

    @PostMapping("/registerTestMe")
    public ModelAndView postRegister(
            HttpServletResponse response,
            @RequestParam(name = "username") String username) {

        final Cookie cookie = new Cookie(STATE_USERNAME_KEY, username);
        cookie.setMaxAge(Integer.MAX_VALUE);

        response.addCookie(cookie);

        return super.redirect("/login");
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        return super.view("demo/register");
    }

    @PostMapping("/register")
    public ModelAndView postRegister(UserRegisterFormDto userRegisterForm,
                                     HttpSession httpSession,
                                     HttpServletRequest request) {
        //HttpSession httpSession2 = request.getSession();

        httpSession.setAttribute(STATE_USERNAME_KEY, userRegisterForm.getUsername());
        return super.redirect("/demo/login");
    }


    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView,
                                  HttpSession httpSession) {
        String fetchedUsername = httpSession
                .getAttribute(STATE_USERNAME_KEY).toString();

        modelAndView.addObject(STATE_USERNAME_KEY, fetchedUsername);

        return super.view("demo/login", modelAndView);
    }
}
