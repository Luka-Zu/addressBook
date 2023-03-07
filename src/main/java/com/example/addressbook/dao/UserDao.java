package com.example.addressbook.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    private final List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("luka.zukhbaia@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User("gio.zukhbaia@gmail.com",
                    "password2",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )

    );


    public UserDetails FindUserByEmail(String email){
        return APPLICATION_USERS.
                stream()
                .filter(u->u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(()->new UsernameNotFoundException("No user was found"));
    }
}
