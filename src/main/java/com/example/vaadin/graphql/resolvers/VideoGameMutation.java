package com.example.vaadin.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.vaadin.models.entities.VideoGame;
import com.example.vaadin.models.services.VideoGameService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class VideoGameMutation implements GraphQLMutationResolver {

    private VideoGameService videoGameService;

    public VideoGameMutation(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    public String InsertVideoGame(String name, String imgFileName, String img64)throws IOException {
        VideoGame videogame = new VideoGame(name, imgFileName);
        String processedImg64 = img64.substring(img64.indexOf(",")+1);
        Decoder dec= Base64.getDecoder();
        OutputStream OS= null;
        byte[] fileBytes = dec.decode(processedImg64);
        OS = new FileOutputStream(new File("src/main/resources/static/img/Videogames", imgFileName));
        OS.write(fileBytes);
        videoGameService.insert(videogame);

        return "Se ha creado el juego";
    }


    public String InsertVideoGameWithOutImage(String name) {

        VideoGame videogame = new VideoGame();

        videogame.setName(name);

        videoGameService.insert(videogame);

        return "Se ha creado el juego";
    }


    public String UpdateVideoGameImage(int id, String imgFileName, String img64)throws IOException {

        VideoGame videogame = new VideoGame();

        videogame.setPhoto(imgFileName);

        String processedImg64 = img64.substring(img64.indexOf(",")+1);
        Decoder dec= Base64.getDecoder();
        OutputStream OS= null;
        byte[] fileBytes = dec.decode(processedImg64);
        OS = new FileOutputStream(new File("src/main/resources/static/img/Videogames", imgFileName));
        OS.write(fileBytes);
        videoGameService.update(videogame, id);

        return "Se ha actualizado el juego";
    }

    public String UpdateVideoGame(int id, String name) {

        VideoGame videogame = new VideoGame();

        videogame.setName(name);

        videoGameService.update(videogame, id);

        return "Se ha actualizado el juego";
    }


    public String DeleteVideoGame ( long id) {
        videoGameService.delete(id);

        return "Se ha borrado el juego";
    }
}
