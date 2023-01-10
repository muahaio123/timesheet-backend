package com.beaconfire.contactservice.dao.impl;

import com.beaconfire.contactservice.dao.AbstractHibernateDAO;
import com.beaconfire.contactservice.dao.ContactDAO;
import com.beaconfire.contactservice.entity.Contact;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("contactDAO")
public class ContactDaoImpl extends AbstractHibernateDAO<Contact> implements ContactDAO {

    public ContactDaoImpl() {
        setClazz(Contact.class);
    }

    @Override
    public Contact getContactById(Integer id) {
        return findById(id);
    }

    @Override
    public void addContact(Contact contact) {
        add(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return getCurrentSession().createQuery("from contacts").list();
    }

    @Override
    public List<Contact> getContactByEmployeeID(Integer id) {
        Query query = getCurrentSession().createQuery("from contacts where employeeId = :employeeId");
        query.setInteger("employeeId", id);
        return  query.list();
    }

    @Override
    public Boolean deleteContact(Integer id) {
        Query query = getCurrentSession().createQuery("delete contacts where id = :id");
        query.setInteger("id", id);
        return query.executeUpdate() != 0;
    }


}