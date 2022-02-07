package com.example.vaadin;

import com.example.vaadin.graphql.resolvers.AppUserMutation;
import com.example.vaadin.graphql.resolvers.AppUserQuery;
import com.example.vaadin.models.services.AppUserService;
import com.example.vaadin.models.services.EncryptService;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EMFactory {


    @Bean
    public AppUserQuery appUserQuery(AppUserService appUserService, EncryptService encryptService)
            throws UnsatisfiedDependencyException {
        return new AppUserQuery ( appUserService,  encryptService);
    }


    @Bean
    public AppUserMutation appUserMutation(AppUserService appUserService, EncryptService encryptService)
            throws UnsatisfiedDependencyException {
        return new AppUserMutation ( appUserService,  encryptService);
    }


}
