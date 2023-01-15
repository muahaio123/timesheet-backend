package com.beaconfire.contactservice.service;

import com.beaconfire.contactservice.dao.ContactDAO;
import com.beaconfire.contactservice.dao.impl.ContactDaoImpl;
import com.beaconfire.contactservice.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {
    private ContactDAO contactDao;

    @Autowired
    @Qualifier("contactDAO")
    public void setContactDao(ContactDaoImpl contactDao) {
        this.contactDao = contactDao;
    }

    @Transactional
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Transactional
    public Contact getContactByID(Integer id) {
        return contactDao.getContactById(id);
    }

    @Transactional
    public List<Contact> getContactByEmployeeID(Integer id) {
        return contactDao.getContactByEmployeeID(id);
    }

    @Transactional
    public void addContact(Contact... contacts) {
        for (Contact contact : contacts) {
            contactDao.addContact(contact);
            System.out.println("Successfully add contact: " + contact.getName());
        }
    }

    @Transactional
    public void deleteContact(Integer id) {
        contactDao.deleteContact(id);
    }

    @Transactional
    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }
}
