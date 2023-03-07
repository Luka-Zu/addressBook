package com.example.addressbook.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String address;
    private String email;




}
