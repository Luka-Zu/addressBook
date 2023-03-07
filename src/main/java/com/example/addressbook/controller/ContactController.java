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
@RequestMapping(value = "/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public ResponseEntity<List<Contact>> getAllUsers() {
        return new ResponseEntity<>(contactService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Contact> getContactByName(@PathVariable String name){
        return new ResponseEntity<>(contactService.getContactByName(name), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public  ResponseEntity<Contact> getContactByID(@PathVariable Long id){
        return new ResponseEntity<>(contactService.getContactByID(id).orElseThrow(EntityNotFoundException::new), HttpStatus.OK);
    }
    @PutMapping("{id}")
    public  ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
        contact.setId(id);

        return new ResponseEntity<>(contactService.updateContact(contact), HttpStatus.CREATED); // or accepted
    }

    @PostMapping
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.saveContact(contact), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public  ResponseEntity deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
