package com.softuni.pathfinder.domain.dto.view;

import com.softuni.pathfinder.domain.entities.Picture;
import com.softuni.pathfinder.domain.entities.Route;

public class RoutePartialViewModel {
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    public RoutePartialViewModel() {
    }

    public RoutePartialViewModel(Long id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public RoutePartialViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoutePartialViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoutePartialViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RoutePartialViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public static RoutePartialViewModel fromRoute(Route route) {
        String picture = route.getPictures()
                .stream()
                .findFirst()
                .orElse(new Picture()
                        .setUrl("http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg"))
                .getUrl();

        return new RoutePartialViewModel(
                route.getId(),
                route.getName(),
                route.getDescription(),
                picture);
    }
}
