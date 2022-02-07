package com.example.vaadin.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.vaadin.models.entities.VideoGame;
import com.example.vaadin.models.services.VideoGameService;

import java.util.List;
import java.util.Optional;

public class VideoGameQuery implements GraphQLQueryResolver {

    private VideoGameService videoGameService;

    public VideoGameQuery(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    public List<VideoGame> getVideogames() {
        return videoGameService.getAll();
    }

    public Optional<VideoGame> getVideogame(long id) {
        return videoGameService.findById(id);
    }

    public boolean getExistVideogame(String name){

        return videoGameService.findByName(name);


    }


    public List<VideoGame> getVideogameByName(String name){

        return videoGameService.findVideoGameByNameContaining(name);

    }
}
