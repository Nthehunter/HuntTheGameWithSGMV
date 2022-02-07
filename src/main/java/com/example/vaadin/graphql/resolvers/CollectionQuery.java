package com.example.vaadin.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.vaadin.models.entities.Collection;
import com.example.vaadin.models.repositories.CollectionRepository;
import com.example.vaadin.models.services.CollectionService;

import java.util.List;

public class CollectionQuery implements GraphQLQueryResolver {

    private CollectionService collectionService;

    private CollectionRepository collectionRepository;

    public CollectionQuery(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public List<Collection> CollectionByIdUser(long idAppUser) {
        return collectionRepository.findByIdAppUser(idAppUser);
    }

    public List<Collection> getCollections() {
        return collectionService.getAll();
    }
}
