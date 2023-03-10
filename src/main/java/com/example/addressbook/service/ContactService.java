package com.example.addressbook.service;

import com.example.addressbook.model.Contact;
import com.example.addressbook.repository.ContactRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact getContactByName(String name){
        return contactRepository.getUserByUsername(name);
    }
    public List<Contact> getAll(){
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactByID(Long id){
        return contactRepository.findById(id);
    }

    public Contact updateContact(Contact contact) {
        contactRepository.deleteById(contact.getId());

        return contactRepository.save(contact);
    }



    public void writeEmployeesToCsv(Writer writer) {

        List<Contact> contacts = contactRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Username", "Address","Email","Image_url");
            for (Contact contact : contacts) {
                csvPrinter.printRecord(contact.getId(), contact.getUsername(), contact.getAddress(), contact.getImageUrl());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }

    public Contact saveContact(Contact contact){
        if(contactRepository.existsById(contact.getId())){
            //TODO
        }
        return contactRepository.save(contact);
    }


}
