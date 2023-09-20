package com.softuni.pathfinder.domain.dto.binding;

import com.softuni.pathfinder.domain.enums.Level;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RouteAddForm {
    @NotNull
    private MultipartFile gpxCoordinates;

    @NotNull
    private Level level;

    @Size(min = 4, max = 20)
    private String name;

    @NotNull
    private String video;

    @Size(min = 20)
    private String description;

    @NotNull
    private List<String> categories;

    public RouteAddForm() {
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddForm setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public RouteAddForm setLevel(Level level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteAddForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public RouteAddForm setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteAddForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<String> getCategories() {
        return categories;
    }

    public RouteAddForm setCategories(List<String> categories) {
        this.categories = categories;
        return this;
    }
}
