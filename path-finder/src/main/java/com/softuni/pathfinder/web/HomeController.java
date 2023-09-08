package com.softuni.pathfinder.web;

import com.softuni.pathfinder.domain.dto.view.MostCommentedRouteViewDto;
import com.softuni.pathfinder.service.RouteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
    private static String USERNAME_SESSION_KEY = "username";
    private final RouteService routeService;

    @Autowired
    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ModelAndView getHome(ModelAndView modelAndView,
                                HttpSession httpSession) {
        String username = httpSession.getAttribute(USERNAME_SESSION_KEY) != null
            ? httpSession.getAttribute(USERNAME_SESSION_KEY).toString()
            : "";

        final MostCommentedRouteViewDto mostCommented =
                this.routeService.getMostCommented();

        modelAndView.addObject("mostCommented", mostCommented);
        modelAndView.addObject(USERNAME_SESSION_KEY, username);

        return super.view("index", modelAndView);
    }
}
