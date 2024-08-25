package greenpark.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import greenpark.dto.Adventures;

public class AdventureDao {
	
	public EntityManager getManager() 
	{	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bhakti");
		EntityManager manager = factory.createEntityManager();
		return manager;
		
	}
	
	public Adventures addAdventure(Adventures adventures) 
	{	
		EntityManager manager = getManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(adventures);
		transaction.commit();
		
		Adventures adventuresdb = manager.find(Adventures.class, adventures.getId());
		return adventuresdb;
	}
	
	public String deleteAdventure(int id) 
	{
		EntityManager manager = getManager();
		Adventures adventures = manager.find(Adventures.class, id);
		
		if(adventures != null) {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(adventures);
			transaction.commit();
			return "success";
		}
		else {
			return "Failed";
		}
	}
	
	public Adventures updateAdventure(int id , Adventures adventures) 
	{
		EntityManager manager = getManager();
		Adventures adventuresdb = manager.find(Adventures.class, id);
		
		if(adventuresdb != null) {
			adventures.setId(id);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			adventuresdb = manager.merge(adventures);
			transaction.commit();
		}
		return adventuresdb;
	}
	
	public List<Adventures> viewAdventures() 
	{
		
		EntityManager manager = getManager();
		Query query = manager.createQuery("SELECT a FROM Adventures a");
		
		 List<Adventures> adventures =  query.getResultList();
		 System.out.println(adventures);
		 return adventures;
		
	}

}
