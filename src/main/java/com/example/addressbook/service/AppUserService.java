package com.example.addressbook.service;

import com.example.addressbook.model.ApplicationUser;
import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    @Autowired
    AppUserRepository appUserRepository;

    public List<ApplicationUser> getAll(){
        return appUserRepository.findAll();
    }

    public ApplicationUser saveUser(ApplicationUser appUser){
        if(appUserRepository.existsById(appUser.getId())){
            //TODO
        }
        return appUserRepository.save(appUser);
    }
}
