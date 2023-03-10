package com.example.addressbook.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String address;

    @Column(length = 2000)
    private String imageUrl;




}
