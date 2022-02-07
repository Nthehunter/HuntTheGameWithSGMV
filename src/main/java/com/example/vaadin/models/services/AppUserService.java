package com.example.vaadin.models.services;

import com.example.vaadin.models.entities.AppUser;
import com.example.vaadin.models.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {


    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService( AppUserRepository appUserRepository ){
        this.appUserRepository = appUserRepository;
    }


    public List<AppUser> getAll() {

        return (List<AppUser>) appUserRepository.findAll();
    }


    public void insert(AppUser AppUser) {

        appUserRepository.save(AppUser);

    }


    public void delete(long id) {
        appUserRepository.deleteById(id);
    }


    public void update(AppUser AppUser, long id) {
        if (appUserRepository.findById(id).isPresent()) {
            AppUser existingAppUser = appUserRepository.findById(id).get();

            try {
                if (AppUser.getemail().length() != 0) {
                    existingAppUser.setemail(AppUser.getemail());
                }

            } catch (Exception e) {

            }

            try {
                if (AppUser.getPassword().length() != 0) {

                    existingAppUser.setPassword(AppUser.getPassword());
                }
            } catch (Exception e) {

            }

            try {

                if (AppUser.getuserName().length() != 0) {


                    if(!appUserRepository.findByuserName(AppUser.getuserName()).isPresent()) {
                        existingAppUser.setuserName(AppUser.getuserName());
                    }


                }
            } catch (Exception e) {

            }

            try {

                if (!AppUser.getphoto().equals(null)) {

                    existingAppUser.setphoto(AppUser.getphoto());
                }
            } catch (Exception e) {

            }


            appUserRepository.save(existingAppUser);

        } else {
            System.out.print("No existe el elemento que se quiere actualizar");
        }

    }


    public Optional<AppUser> findById(Long id) {

        return appUserRepository.findById(id);
    }


    public AppUser loadUserByEmail(String email) throws UsernameNotFoundException {

        final Optional<AppUser> optionalUser = appUserRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            return (AppUser) optionalUser.get();
        }
        else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }



    public boolean findUserByUserName(String userName) {

        return appUserRepository.findByuserName(userName).isPresent();
    }


    public boolean findUserByUserEmail(String email) {

        return appUserRepository.findByEmail(email).isPresent();
    }


    public List<AppUser> findUserByUsernameLike(String userName) {
        List<AppUser> Users = appUserRepository.findUserByuserNameContaining(userName);
        int position = 0;
        for (AppUser appUser : Users) {
            if(appUser.getRol() == 1) {
                Users.remove(position);
                break;
            }
            else {
                position++;
            }
        }

        return Users;
    }

}
