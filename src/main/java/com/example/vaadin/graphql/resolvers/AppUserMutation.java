package com.example.vaadin.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.vaadin.models.entities.AppUser;
import com.example.vaadin.models.services.AppUserService;
import com.example.vaadin.models.services.EncryptService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class AppUserMutation implements GraphQLMutationResolver {

    private AppUserService appUserService;

    private EncryptService encryptService;

    public AppUserMutation(AppUserService appUserService, EncryptService encryptService){
        this.appUserService = appUserService;
        this.encryptService = encryptService;
    }

    public String InsertAppUser(String email, String password , String userName, String imgFileName ,String img64)throws IOException {
        String hashPass = encryptService.encryptPassword(password);
        AppUser user = new AppUser(email, hashPass, userName, imgFileName);
        String processedImg64 = img64.substring(img64.indexOf(",")+1);
        Base64.Decoder dec= Base64.getDecoder();
        OutputStream OS= null;
        byte[] fileBytes = dec.decode(processedImg64);
        OS = new FileOutputStream(new File("src/main/resources/static/img/AppUsers", imgFileName));
        OS.write(fileBytes);
        appUserService.insert(user);
        return "Usuario creado :,)";
    }

    public String InsertAppUserWithOutImage( String email, String password , String userName )  {
        String hashPass = encryptService.encryptPassword(password);
        AppUser user = new AppUser(email, hashPass, userName);
        appUserService.insert(user);
        return "Usuario creado :,)";

    }

    public String InsertAdmin( String email, String password , String userName, int rol )  {
        String hashPass = encryptService.encryptPassword(password);
        AppUser user = new AppUser(email, hashPass, userName);
        appUserService.insert(user);
        return "Admin creado :,)";

    }

    public String UpdateAppUser(Long id, String email, String password , String userName, String imgFileName ,String img64 )throws IOException {

        AppUser user = new AppUser();

        user.setemail(email);
        user.setPassword(password);
        user.setuserName(userName);
        user.setphoto(img64);

        if(user.getPassword() != null) {
            String hashPass = encryptService.encryptPassword(user.getPassword());
            user.setPassword(hashPass);
        }
        String processedImg64 = img64.substring(img64.indexOf(",")+1);
        Decoder dec= Base64.getDecoder();
        OutputStream OS= null;
        byte[] fileBytes = dec.decode(processedImg64);
        OS = new FileOutputStream(new File("src/main/resources/static/img/AppUsers", imgFileName));
        OS.write(fileBytes);
        appUserService.update(user, id);

        return "Se ha actualizado";
    }

    public String UpdateImgUser(Long id, String imgFileName ,String img64 )throws IOException {
        AppUser user = new AppUser(imgFileName);
        String processedImg64 = img64.substring(img64.indexOf(",")+1);
        Decoder dec= Base64.getDecoder();
        OutputStream OS= null;
        byte[] fileBytes = dec.decode(processedImg64);
        OS = new FileOutputStream(new File("src/main/resources/static/img/AppUsers", imgFileName));
        OS.write(fileBytes);
        appUserService.update(user, id);

        return "Se ha actualizado";
    }


    public String DeleteAppUser ( long id) {
        appUserService.delete(id);

        return "Se ha borrado el usuario";
    }

}
