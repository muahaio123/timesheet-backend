package com.beaconfire.authservice.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractDAO<T extends Serializable> {
    protected SessionFactory sessionFactory;

    @Autowired
    @Qualifier("hibernateSessionFactory")
    public void getSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<T> clazz;

    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    protected List<T> getAll() {
        @SuppressWarnings("unchecked")
        List<T> objects = (List<T>) getCurrentSession().createQuery("FROM " + clazz.getName()).list();
        return objects.size() == 0 ? null : objects;
    }

    protected T findById(Integer id) {
        return getCurrentSession().get(clazz, id);
    }

    protected List<T> findByForeignKey(String foreignKey, Integer id) {
        @SuppressWarnings("unchecked")
        List<T> objects = (List<T>) getCurrentSession().createQuery(String.format("FROM %s WHERE %s = %d", clazz.getName(), foreignKey, id)).list();

        return objects.size() == 0 ? null : objects;
    }

    protected Integer addObject(T object) {
        return (Integer) getCurrentSession().save(object);
    }

    protected void updateObject(T object) {
        getCurrentSession().update(object);
    }

    protected void deleteObject(T object) {
        getCurrentSession().delete(object);
    }
}
