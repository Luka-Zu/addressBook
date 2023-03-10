package com.example.addressbook.dao;

import com.example.addressbook.model.ApplicationUser;
import com.example.addressbook.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDao {

    @Autowired
    private AppUserService appUserService;
    private List<UserDetails> APPLICATION_USERS ;

    public AppUserService getAppUserService() {
        return appUserService;
    }

    public void addAccount(ApplicationUser applicationUser){

    }

    public void updateAccounts(){
        APPLICATION_USERS = appUserService
                .getAll()
                .stream()
                .map(
                        acc -> new User(acc.getEmail()
                                , acc.getPassword()
                                , Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
                        ))
                .collect(Collectors.toList());
    }

    public UserDetails FindUserByEmail(String email){
        updateAccounts();

        return APPLICATION_USERS.
                stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("No user was found"));
    }
}
