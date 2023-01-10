package com.beaconfire.documentservice.DAO;

import com.beaconfire.documentservice.domain.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDAO extends AbstractHibernateDAO<Document>{
    public DocumentDAO(){setClazz(Document.class);}

    public List<Document> getAllDocuments(){
        return getCurrentSession().createQuery("from Document ").list();
    }

    public void addDocument(Document document) {
        add(document);
    }
}
