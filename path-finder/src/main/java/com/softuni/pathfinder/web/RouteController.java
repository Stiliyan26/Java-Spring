package com.softuni.pathfinder.web;

import com.softuni.pathfinder.domain.dto.binding.CategoryAddForm;
import com.softuni.pathfinder.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/add")
    public ModelAndView getAdd(ModelAndView modelAndView) {
        return super.view("add-route", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView postAdd(@Valid CategoryAddForm categoryAddForm,
                                BindingResult bindingResult,
                                ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return super.view("add-route", modelAndView);
        }

        return super.view("add-route", modelAndView);
    }

    //ModelAttributes
    @ModelAttribute(name = "routeAddForm")
    public CategoryAddForm initCategoryAddForm() {
        return new CategoryAddForm();
    }
}
