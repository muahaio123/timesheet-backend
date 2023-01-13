package com.beaconfire.documentservice.DAO;

import com.beaconfire.documentservice.domain.Document;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDAO extends AbstractHibernateDAO<Document>{
    public DocumentDAO(){setClazz(Document.class);}

    public List<Document> getAllDocuments(){
        return getCurrentSession().createQuery("from Document ").list();
    }

    public List<Document> getDocumentById(Integer id){
        Query q = getCurrentSession().createQuery("from Document where id = :id");
        q.setParameter("id", id);
        return q.list();
    }

    public List<Document> getDocumentByEmployeeId(Integer employeeId){
        Query q = getCurrentSession().createQuery("from Document where EmployeeID = :employeeId");
        q.setParameter("employeeId", employeeId);
        return q.list();
    }

    public void addDocument(Document document) {
        add(document);
    }

    public void deleteByDocumentId(Integer id){
        getCurrentSession().createNativeQuery("delete from documents where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Document> getDocumentByTimeSheetId(Integer timesheetId){
        Query q = getCurrentSession().createQuery("from Document where TimeSheetId = :timesheetId");
        q.setParameter("timesheetId", timesheetId);
        return q.list();

    }
}
