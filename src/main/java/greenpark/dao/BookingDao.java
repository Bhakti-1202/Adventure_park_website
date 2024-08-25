package greenpark.dao;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import greenpark.dto.Adventures;
import greenpark.dto.Booking;
import greenpark.dto.User;

public class BookingDao 
{
	public EntityManager getManager() 
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bhakti") ;
		EntityManager manager = factory.createEntityManager() ;
		return manager ;
	}	
	
	public Booking book(Booking booking, List<String> list, String email) 
	{
		EntityManager manager = getManager() ;
		Query query = manager.createQuery("select u from User u where email = ?1") ;
		query.setParameter(1, email) ;
		User user = (User) query.getSingleResult() ;
		List<Adventures> adventures = new ArrayList<Adventures>() ;
		for(int i=0; i<list.size(); i++)
		{
			Adventures dbAdventures = new Adventures() ;
			dbAdventures = manager.find(Adventures.class, Integer.parseInt(list.get(i))) ;
			adventures.add(dbAdventures) ;
		}
		booking.setAdventures(adventures);
		
		List<Booking> bookings = user.getBookings() ;
		bookings.add(booking) ;
		user.setBookings(bookings);
		
		EntityTransaction transaction = manager.getTransaction() ;
		transaction.begin(); 
		manager.persist(booking);
		for(Adventures adv : adventures)
		{
			manager.merge(adv) ;
 		}
		manager.merge(user) ;
		transaction.commit();
		
		Booking dbBooking = manager.find(Booking.class, booking.getId()) ;
		return dbBooking ;
	}
	
	public String cancelBooking(int userId, int bookingId) 
	{	
		EntityManager manager = getManager();
		Booking booking = manager.find(Booking.class,bookingId);
		User user = manager.find(User.class, userId);
		
		if(booking != null && user != null) {
			List<Adventures> adventures = booking.getAdventures();
			List<Booking> bookings = user.getBookings();
			bookings.remove(booking);
			user.setBookings(bookings);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.remove(booking);
			for (Adventures adventures2 : adventures) {
				manager.merge(adventures2);
			}
			manager.merge(user);
			transaction.commit();
			
			return "success";
		}
		else {
			return "failed";
		}	
	}
	
	public Booking viewBooking(int id) 
	{
		EntityManager manager = getManager() ;
		Booking booking = manager.find(Booking.class, id) ;
		if (booking != null) 
		{
			return booking ;
		}
		return null ;
	}
	
	public double gettotalCost(int noOfMembers , List<String> list) 
	{
		EntityManager manager = getManager() ;
		double tc = 0;
		for(int i=0; i<list.size(); i++)
		{
			Adventures adventures = manager.find(Adventures.class, Integer.parseInt(list.get(i))) ;
			double price = adventures.getPrice() ;
			tc += price ;	
		}
		return (tc*noOfMembers) ;
	}
	
	
	public List<Booking> viewBookingList()
	{
		EntityManager manager = getManager();
		Query query = manager.createQuery("SELECT b FROM Booking b ");
		List<Booking> bookings = query.getResultList();
		
		return bookings ;
	}
	
	
	public Booking deleteBooking(int id) 
	{
		EntityManager manager = getManager() ;
		Booking booking = manager.find(Booking.class, id) ;
		if (booking != null) 
		{
			EntityTransaction transaction = manager.getTransaction() ;
			transaction.begin();
			manager.remove(booking); 
			transaction.commit();
			return booking ;
		}
		return null ;
	}
	
	
	public void ascendingOrder() 
	{
		EntityManager manager = getManager() ;
		Query query = manager.createQuery("select b from Booking b") ;
		List<Booking> bookings = query.getResultList() ;
		
		Collections.sort(bookings, Comparator.comparing(Booking::getName)) ;
		System.out.println(bookings);
	}

}
