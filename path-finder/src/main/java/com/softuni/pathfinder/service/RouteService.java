package com.softuni.pathfinder.service;

import com.softuni.pathfinder.domain.beans.LoggedUser;
import com.softuni.pathfinder.domain.dto.binding.RouteAddForm;
import com.softuni.pathfinder.domain.dto.model.RouteModel;
import com.softuni.pathfinder.domain.dto.view.RoutePartialViewModel;
import com.softuni.pathfinder.domain.entities.Category;
import com.softuni.pathfinder.domain.entities.Route;
import com.softuni.pathfinder.domain.entities.User;
import com.softuni.pathfinder.domain.enums.CategoryName;
import com.softuni.pathfinder.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final LoggedUser loggedUser;
    private final CategoryService categoryService;

    @Autowired
    public RouteService(RouteRepository routeRepository,
                        ModelMapper modelMapper,
                        UserService userService,
                        LoggedUser loggedUser,
                        CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.categoryService = categoryService;
    }

    public RoutePartialViewModel getMostCommented() {
        return RoutePartialViewModel.fromRoute(
                this.routeRepository
                        .findMostCommented()
                        .orElseThrow(NoSuchElementException::new)
                        .get(0)
        );
    }

    public List<RoutePartialViewModel> getAllRoutesPartialViews() {
        return this.routeRepository
                .findAll()
                .stream()
                .map(RoutePartialViewModel::fromRoute)
                .toList();
    }

    public void addNewRoute(RouteAddForm routeAddForm) throws IOException {
        Route route = this.modelMapper
                .map(routeAddForm, Route.class);


        Set<Category> categories = routeAddForm.getCategories()
                .stream()
                .map(cm -> this.categoryService.findByName(CategoryName.valueOf(cm)))
                .collect(Collectors.toSet());

        User author = this.modelMapper.map(this.userService
                .findByUsername(loggedUser.getUsername()), User.class);


        route
                .setAuthor(author)
                .setCategories(categories)
                .setGpxCoordinates(new String(routeAddForm.getGpxCoordinates().getBytes()));

        this.routeRepository.saveAndFlush(route);
    }

    @Transactional
    public RouteModel findById(Long id) {
        return this.modelMapper
            .map(this.routeRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new), RouteModel.class);
    }

    public List<RoutePartialViewModel> findAllByCategoryName(String categoryName) {
        Category category = this.categoryService
                .findByName(CategoryName.valueOf(categoryName.toUpperCase()));

        return this.routeRepository
                .findAllByCategoriesContains(category)
                .orElseThrow()
                .stream()
                .map(RoutePartialViewModel::fromRoute)
                .toList();
    }
}
