package com.example.vaadin.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.vaadin.models.entities.AppUser;
import com.example.vaadin.models.services.AppUserService;
import com.example.vaadin.models.services.EncryptService;

import java.util.List;
import java.util.Optional;

public class AppUserQuery implements GraphQLQueryResolver {

    private AppUserService appUserService;

    private EncryptService encryptService;

    public AppUserQuery(AppUserService appUserService, EncryptService encryptService) {
        this.appUserService = appUserService;
        this.encryptService = encryptService;
    }

    public List<AppUser> getUsers() {
        return appUserService.getAll();
    }

    public Optional<AppUser> getUser(long id) {
        return appUserService.findById(id);
    }

    public List<AppUser> getUserByUserNameLike(String userName) {

        return appUserService.findUserByUsernameLike(userName);

    }

    public boolean getExistUserName(String userName) {

        return appUserService.findUserByUserName(userName);


    }

    public boolean getExistEmail(String email) {

        return appUserService.findUserByUserEmail(email);


    }

    public Optional<AppUser> getLogin(String email, String originalPassword) {

        AppUser loginUser = appUserService.loadUserByEmail(email);

        if(encryptService.verifyPassword(originalPassword, loginUser.getPassword())) {
            return appUserService.findById( loginUser.getIdAppUser());
        }
        else {

            return null;
        }

    }
}
