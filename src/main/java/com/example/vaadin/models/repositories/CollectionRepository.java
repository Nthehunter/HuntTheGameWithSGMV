package com.example.vaadin.models.repositories;

import com.example.vaadin.models.entities.Collection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectionRepository extends CrudRepository<Collection, Long> {
    List<Collection> findByIdAppUser(Long idAppUser);
    Collection findByIdAppUserAndIdVideoGame(Long idAppUser, Long idVideoGame);
}