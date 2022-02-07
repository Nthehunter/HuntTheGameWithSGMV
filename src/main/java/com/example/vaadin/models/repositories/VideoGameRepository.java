package com.example.vaadin.models.repositories;

import com.example.vaadin.models.entities.VideoGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoGameRepository extends CrudRepository<VideoGame, Long> {
    List<VideoGame> findVideoGameByNameContaining(String Name);
    Optional<VideoGame> findByName(String Name);
}
