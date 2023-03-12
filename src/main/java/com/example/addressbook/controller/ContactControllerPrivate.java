package com.example.addressbook.controller;

import com.example.addressbook.model.Contact;
import com.example.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(value = "/contacts/private")
public class ContactControllerPrivate {
    @Autowired
    private ContactService contactService;

    @PutMapping("{id}")
    public  ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        Optional<Contact> existingContact = contactService.getContactByID(id);

        if (existingContact.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else{

            Contact contact1 = existingContact.get();
            contact1.setAddress(contact.getAddress());
            contact1.setId(id);
            contact1.setImageUrl(contact.getImageUrl());
            contact1.setUsername(contact.getUsername());
            Contact updatedContact = contactService.updateContact(contact1);
            return ResponseEntity.ok(updatedContact); // or accepted
        }

    }

    @PostMapping()
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.saveContact(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
