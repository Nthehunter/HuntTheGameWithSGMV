package com.example.vaadin.models.services;

import com.example.vaadin.models.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public EncryptService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public boolean verifyPassword(String originalPassword, String hashPassword) {

        return BCrypt.checkpw(originalPassword, hashPassword);
    }

    public String encryptPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
