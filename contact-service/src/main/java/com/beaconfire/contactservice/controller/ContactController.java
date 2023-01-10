package com.beaconfire.contactservice.controller;

import com.beaconfire.contactservice.entity.Contact;
import com.beaconfire.contactservice.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContact() {
        return contactService.getAllContacts();
    }

    @GetMapping("getContactByID/{id}")
    public Contact getContactByID(@PathVariable Integer id) {
        return contactService.getContactByID(id);
    }

    @GetMapping("getContactsByEmployeeID/{id}")
    public List<Contact> getContactByEmployeeID(@PathVariable Integer id) {
        return contactService.getContactByEmployeeID(id);
    }

    @PostMapping("createContact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) throws SQLException {
        contactService.addContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @DeleteMapping("deleteContact/{id}")
    public void deleteContactById(@PathVariable Integer id) {
        contactService.deleteContact(id);
    }
}

