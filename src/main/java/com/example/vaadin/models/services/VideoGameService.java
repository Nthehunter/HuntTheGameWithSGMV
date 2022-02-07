package com.example.vaadin.models.services;


import com.example.vaadin.models.entities.VideoGame;
import com.example.vaadin.models.repositories.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGameService {

    private final VideoGameRepository videoGameRepository;

    @Autowired
    public VideoGameService( VideoGameRepository videoGameRepository ){
        this.videoGameRepository = videoGameRepository;
    }


    public List<VideoGame> getAll() {

        return (List<VideoGame>) videoGameRepository.findAll();
    }


    public void insert(VideoGame VideoGame) {
        videoGameRepository.save(VideoGame);

    }


    public void delete(long id) {
        videoGameRepository.deleteById(id);

    }


    public void update(VideoGame videogame, long id) {
        if (videoGameRepository.findById(id).isPresent()) {
            VideoGame existingGame = videoGameRepository.findById(id).get();

            System.out.println(videogame.getName());

            try {
                if (videogame.getName().length() != 0) {
                    existingGame.setName(videogame.getName());
                }

            } catch (Exception e) {

            }


            try {

                if (!videogame.getPhoto().equals(null)) {

                    existingGame.setPhoto(videogame.getPhoto());
                }
            } catch (Exception e) {

            }

            videoGameRepository.save(existingGame);
        }

    }


    public Optional<VideoGame> findById(Long id) {

        return videoGameRepository.findById(id);

    }


    public List<VideoGame> findVideoGameByNameContaining(String Name) {

        return videoGameRepository.findVideoGameByNameContaining(Name);
    }


    public boolean findByName(String Name) {

        return videoGameRepository.findByName(Name).isPresent();
    }

}
