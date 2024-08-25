package greenpark.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import greenpark.dto.Admin;
import greenpark.dto.Adventures;

public class AdminDao {
	
	public EntityManager getManager()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bhakti");
		EntityManager manager = factory.createEntityManager();
		
		return manager;
	}
	
	public Admin regiterAdmin(Admin admin) 
	{	
		EntityManager manager = getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(admin);
		transaction.commit();
		
		return manager.find(Admin.class, admin.getId());
	}
	public Admin adminLogin(String email ) 
	{
		EntityManager manager = getManager();
		Query query = manager.createQuery("SELECT a FROM Admin a WHERE EMAIL = email");
		Admin admin = (Admin) query.getSingleResult();
		
		if(admin != null) {
			return admin;
		}
		else {
			return null;
		}
	}
	
	public Admin deleteAdmin(int id) 
	{	
		EntityManager manager = getManager();
		Admin admin = manager.find(Admin.class, id);
		
		if(admin != null) {
			List<Adventures> adventures = admin.getAdventures();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(admin);
			for (Adventures adventures2 : adventures) {
				manager.merge(adventures2);
			}
			transaction.commit();
			
			return admin;
		}
		else 
		{
			return null ;
		}	
	}
}