package com.example.addressbook.controller;

import com.example.addressbook.model.Contact;
import com.example.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts/private")
public class ContactControllerPrivate {
    @Autowired
    private ContactService contactService;

    @PutMapping("{id}")
    public  ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        contact.setId(id);

        return new ResponseEntity<>(contactService.updateContact(contact), HttpStatus.CREATED); // or accepted
    }

    @PostMapping({"{id}"})
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.saveContact(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
