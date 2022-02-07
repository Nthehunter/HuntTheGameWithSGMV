package com.example.vaadin.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.vaadin.models.entities.Collection;
import com.example.vaadin.models.services.CollectionService;

public class CollectionMutation implements GraphQLMutationResolver {

    private CollectionService collectionService;

    public CollectionMutation(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    public String InsertCollection ( long idAppUser, long idVideoGame, int State, int GameTime) {

        Collection collection = new Collection(idAppUser, idVideoGame, State, GameTime);

        collectionService.insert(collection);

        return "Se ha añadido el videojuego a la colección";
    }

    public String UpdateVideoGameInCollection ( long idAppUser, long idVideoGame, int State, int GameTime) {

        Collection collection = new Collection(idAppUser, idVideoGame, State, GameTime);

        collectionService.update(collection);

        return "Se ha actualizado el videojuego de la colección";
    }

    public String DeleteVideoGameInCollection ( long idAppUser, long idVideoGame) {

        Collection collection = new Collection();
        collection.setIdAppUser(idAppUser);
        collection.setIdVideoGame(idVideoGame);

        collectionService.delete(collection);

        return "Se ha eliminado el videojuego de la colección";
    }

    public String CompleteVideoGame ( long idAppUser, long idVideoGame, int GameTime) {

        Collection collection = new Collection(idAppUser, idVideoGame, 1, GameTime);

        collectionService.update(collection);

        return "Se ha actualizado el estado del videojuego de la colección";
    }

    public String NotCompleteVideoGame ( long idAppUser, long idVideoGame, int GameTime) {

        Collection collection = new Collection(idAppUser, idVideoGame, 0, GameTime);

        collectionService.update(collection);

        return "Se ha actualizado el estado del videojuego de la colección";
    }

}
