package com.softuni.pathfinder.domain.dto.view;

import com.softuni.pathfinder.domain.entities.Picture;
import com.softuni.pathfinder.domain.entities.Route;

import java.util.NoSuchElementException;
import java.util.Set;

public class MostCommentedRouteViewDto {
    private String name;

    private String description;

    private String imageUrl;

    public MostCommentedRouteViewDto(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public MostCommentedRouteViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MostCommentedRouteViewDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MostCommentedRouteViewDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public static MostCommentedRouteViewDto fromRoute(Route route) {
        String picture = route.getPictures()
                .stream()
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .getUrl();

        return new MostCommentedRouteViewDto(route.getName(),
                route.getDescription(),
                picture);
    }
}
