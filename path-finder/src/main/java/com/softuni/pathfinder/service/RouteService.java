package com.softuni.pathfinder.service;

import com.softuni.pathfinder.domain.dto.view.RoutePartialViewModel;
import com.softuni.pathfinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RouteService {
    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public RoutePartialViewModel getMostCommented(){
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
}
