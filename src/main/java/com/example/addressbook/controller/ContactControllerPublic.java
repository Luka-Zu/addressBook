package com.example.addressbook.controller;

import com.example.addressbook.model.Contact;
import com.example.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/contacts/public")
public class ContactControllerPublic {
    @Autowired
    private ContactService contactService;

    @RequestMapping(path = "/csv")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"employees.csv\"");
        contactService.writeEmployeesToCsv(servletResponse.getWriter());
    }

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

}
