package repository;

import entity.Document;
import entity.Request;
import entity.Residence;
import entity.User;
import utils.CloneObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.Doc;
import java.util.List;
import java.util.UUID;

public class DocumentRepo implements IRepository<Document>{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");


    @Override
    public void insertEntry(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(document);
        em.getTransaction().commit();
      //  em.close();
    }

    @Override
    public Document findByInt(Integer criterion) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document document = em.find(Document.class, criterion);
        em.getTransaction().commit();
        return document;
    }

    @Override
    public Document findByString(String criterion) {
        return null;
    }


    public Document findByType(String docType) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return (Document) em.createQuery("SELECT doc FROM Document doc WHERE doc.type_of_doc= '" + docType+"'").getSingleResult();
    }


    @Override
    public void updateEntry(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document newDoc = findByInt(document.getDocument_id());
        em.detach(newDoc);
        CloneObject.CloneDocument(newDoc,document);
        em.merge(newDoc);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public void deleteEntry(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document reqq = em.getReference(Document.class, document.getDocument_id());
        em.remove(reqq);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public List<Document> queryForAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        return  em.createQuery("SELECT doc FROM Document doc").getResultList();
    }




}
