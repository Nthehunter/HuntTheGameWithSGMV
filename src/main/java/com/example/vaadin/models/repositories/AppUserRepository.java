package com.example.vaadin.models.repositories;

import com.example.vaadin.models.entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByuserName(String userName);
    List<AppUser> findUserByuserNameContaining(String userName);

}
