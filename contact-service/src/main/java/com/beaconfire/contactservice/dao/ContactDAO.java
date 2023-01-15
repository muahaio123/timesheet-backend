package com.beaconfire.contactservice.dao;


import com.beaconfire.contactservice.entity.Contact;

import java.util.List;

public interface ContactDAO {

    Contact getContactById(Integer id);

    void addContact(Contact contact);

    List<Contact> getAllContacts();

    List<Contact> getContactByEmployeeID(Integer id);

    Boolean deleteContact(Integer id);

    void updateContact(Contact contact);
}
