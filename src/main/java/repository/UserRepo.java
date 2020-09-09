package repository;

import entity.User;
import utils.CloneObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

public class UserRepo implements IRepository<User>{
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
	//private EntityManager em;

	public UserRepo(){
		//em = entityManagerFactory.createEntityManager();
	}

	@Override
	public void insertEntry(User user){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public User findByInt(Integer criterion) {
		return null;
	}


	@Override
	public User findByString(String criterion) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User person = em.find(User.class, criterion);
		em.getTransaction().commit();
		return person;
	}

	@Override
	public void updateEntry(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User newUser = findByString(user.getUser_id());
		em.detach(newUser);
		CloneObject.CloneUser(newUser,user);
		em.merge(newUser);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void deleteEntry(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}

	// SORTING AND FILTERING

	@Override
	public List<User> queryForAll(){
		EntityManager em = entityManagerFactory.createEntityManager();
		List<User> users = em.createQuery("SELECT a FROM User a ").getResultList();
		return users;
	}

	public List<User> queryForAllWithUserType(String user_type){
		EntityManager em = entityManagerFactory.createEntityManager();
		List<User> users = em.createQuery("SELECT a FROM User a WHERE a.user_type= '" + user_type +"'").getResultList();
		return users;
	}
	public List<String> queryForAllWith(String criterion){
		EntityManager em = entityManagerFactory.createEntityManager();

	 	Query qr = em.createQuery("SELECT a."+ criterion +" FROM User a WHERE a.user_type = 'user'");
	 	List<String> users = qr.getResultList();
		return users;
	}

	public List<User> sortUsersAfter(String criterion){
		//user type
		EntityManager em = entityManagerFactory.createEntityManager();
		return null;
	}

	public User findByUsername(String username){
		EntityManager em = entityManagerFactory.createEntityManager();
		User user = (User) em.createQuery("SELECT a FROM User a WHERE a.user_name = '"+ username+"'").getSingleResult();
		return user;
	}


}
