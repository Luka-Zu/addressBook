package com.example.addressbook.controller;

import com.example.addressbook.config.JwtUtils;
import com.example.addressbook.dao.UserDao;
import com.example.addressbook.dto.AuthenticationRequest;
import com.example.addressbook.model.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping(value = "/pe/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest request){
        System.out.println("logined");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDao.FindUserByEmail(request.getEmail());
        if(user!=null){
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ApplicationUser user){



        userDao.getAppUserService().saveUser(user);

        userDao.updateAccounts();
        final UserDetails account = userDao.FindUserByEmail(user.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        return ResponseEntity.ok("Registered please login");
    }
}
