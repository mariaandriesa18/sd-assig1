package repository;
import entity.Request;
import entity.Residence;
import utils.CloneObject;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public class RequestRepo  implements IRepository<Request>{
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    @Override
    public void insertEntry(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Request findByInt(Integer criterion) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request request = em.find(Request.class, criterion);
        em.getTransaction().commit();
        return request;
    }

    @Override
    public Request findByString(String criterion) {
        return null;
    }

    @Override
    public void updateEntry(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(request);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteEntry( Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request reqq = em.getReference(Request.class, request.getRequest_id());
        em.remove(reqq);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Request> queryForAll() {
        return null;
    }


    public List<Request> queryForAllByUserId(String userId){
        EntityManager em = entityManagerFactory.createEntityManager();
        Query qr = em.createQuery("SELECT r FROM Request r WHERE r.user.user_id = '" +userId+"'");
        return (List<Request>) qr.getResultList();
    }

    public List<Request> querryForAllWithDocument(String doc){
        EntityManager em = entityManagerFactory.createEntityManager();
        Query qr = em.createQuery("SELECT r FROM Request r WHERE r.document.type_of_doc = '" +doc+"'");
        return (List<Request>) qr.getResultList();
    }

    public  Request findRequestByDocument(String doc){
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            Request request = (Request) em.createQuery("SELECT r FROM Request r WHERE r.document.type_of_doc = '" + doc +"'").getSingleResult();
            return request;
        }catch (NoResultException e){
            return null;
        }

    }


}
