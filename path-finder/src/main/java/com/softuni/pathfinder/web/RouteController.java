package com.softuni.pathfinder.web;

import com.softuni.pathfinder.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/routes")
public class RouteController extends BaseController{
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ModelAndView getAllRoutes(ModelAndView modelAndView) {
        return super.view("routes",
                modelAndView.addObject("routes", this.routeService
                        .getAllRoutesPartialViews()));
    }
}
