package repository;

import entity.Document;
import entity.Residence;
import entity.User;
import utils.CloneObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ResidenceRepo implements  IRepository<Residence>{


    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    @Override
    public void insertEntry(Residence residence) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(residence);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Residence findByString(String criterion) {
        return null;
    }

    @Override
    public Residence findByInt(Integer criterion) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Residence res = em.find(Residence.class, criterion);
        em.getTransaction().commit();
        return res;
    }

    @Override
    public void updateEntry(Residence residence) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Residence newRes = findByInt(residence.getResidence_id());
        em.detach(newRes);
        CloneObject.CloneResidence(newRes,residence);
        em.merge(newRes);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteEntry(Residence residence) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(residence);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Residence> queryForAll() {
        return null;
    }

    public List<Residence> queryForAllFromUser(String userId){
        EntityManager em = entityManagerFactory.createEntityManager();
        return  em.createQuery("SELECT res FROM Residence res WHERE res.user.user_id= '" + userId +"'").getResultList();

    }

    public Residence findByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return (Residence) em.createQuery("SELECT res FROM Residence res WHERE res.residence_name= '" + name+"'").getSingleResult();
    }

}
