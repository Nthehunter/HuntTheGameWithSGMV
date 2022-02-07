package com.example.vaadin.models.services;

import com.example.vaadin.models.entities.Collection;
import com.example.vaadin.models.repositories.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService( CollectionRepository collectionRepository ){
        this.collectionRepository = collectionRepository;
    }

    public List<Collection> getAll() {
        return (List<Collection>) collectionRepository.findAll();
    }

    public void insert(Collection collection) {

        Collection existingCollection = collectionRepository.findByIdAppUserAndIdVideoGame(collection.getIdAppUser(), collection.getIdVideoGame());
        try {
            if(existingCollection.equals(null)) {
                collectionRepository.save(collection);
            }


        }catch ( Exception e){
            System.out.println("ERROR");
            collectionRepository.save(collection);
        }



    }

    public Object findById(Long id) {
        return collectionRepository.findById(id);
    }

    public void update(Collection collection) {

        Collection existingCollection = collectionRepository.findByIdAppUserAndIdVideoGame(collection.getIdAppUser(), collection.getIdVideoGame());


        try {
            if (collection.getGameTime() > 0 ) {

                existingCollection.setGameTime(collection.getGameTime());
            }
        } catch (Exception e) {

        }

        try {

            if (collection.getState() == 0 || collection.getState() == 1 ) {

                existingCollection.setState(collection.getState());
            }
        } catch (Exception e) {

        }

        collectionRepository.save(existingCollection);
    }

    public void delete(Collection collection) {
        collectionRepository.delete(collection);

    }


}
