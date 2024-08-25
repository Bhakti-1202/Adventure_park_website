package greenpark.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import greenpark.controller.Mail;
import greenpark.dto.Adventures;
import greenpark.dto.Booking;
import greenpark.dto.User;

public class UserDao 
{
	public static int otp ; 
	public EntityManager getManager() 
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bhakti") ;
		EntityManager manager = factory.createEntityManager() ;
		return manager ;
	}
	
	public User login(String email) 
	{
		EntityManager manager = getManager() ;
		Query query = manager.createQuery("select u from User u where email=?1") ;
		query.setParameter(1, email) ;
		User user ;
		try {
			 user = (User) query.getSingleResult() ;
			 if (user != null) 
				{
					return user ;
				}
			}
			catch(NoResultException e)
			{
				return null ;
			}
		
		return null ;
	}
	
	public User register(User user) 
	{
		EntityManager manager = getManager() ;
		EntityTransaction transaction = manager.getTransaction() ;
		transaction.begin(); 
		manager.persist(user);
		transaction.commit();
		
		User dbUser = manager.find(User.class, user.getId()) ;
		if (dbUser != null) 
		{
			return dbUser ;
 		}
		return null ;
	}
	
	public User editProfile(User user, String email) 
	{
		EntityManager manager = getManager() ;
		Query query = manager.createQuery("select u from User u where email =?1") ;
		query.setParameter(1, email) ;
		User dbUser = (User) query.getSingleResult() ;
		if (dbUser != null) 
		{
			user.setId(dbUser.getId());
			user.setGender(dbUser.getGender());
			EntityTransaction transaction = manager.getTransaction() ;
			transaction.begin();
			manager.merge(user) ;
			transaction.commit();
			return user ;
		}
		return null ;
	}
	
	public String forgotPassword(String email) 
	{
		String from = "rushijadhavrj9566@gmail.com" ;
//		String to = "rushikeshjadhav0201@gmail.com" ;
		String to = email ;
		
		Random random = new Random() ;
		otp = random.nextInt(10000) ;
		
		Properties properties = System.getProperties() ;
		properties.put("mail.smtp.host", "smtp.gmail.com") ; 
		properties.put("mail.smtp.port", "465") ;
		properties.put("mail.smtp.ssl.enable", "true") ;
		properties.put("mail.smtp.auth", "true") ; 
		//setting the properties of SMTP to send mails.
		
		Session session = Session.getInstance(properties, new Mail()) ; 
		//providing login credentials through authenticator implementing class to session.
		
		session.setDebug(true) ;
		
		try 
		{
			InternetAddress address = new InternetAddress(from);
			//with the help of InternetAddress class, we are converting String type of email address into actual email address.
			InternetAddress address2 = new InternetAddress(to) ;
			
			MimeMessage message = new MimeMessage(session) ;
			//MimeMessage class is used to compose message.
			
			message.setFrom(address);
			message.addRecipient(RecipientType.TO, address2);
			message.setSubject("Regarding password Reset");
			message.setText("Hello, Don't share your OTP with anyone. Your OTP is: " + otp);
			
			Transport.send(message);
			return "success" ;
		} catch (MessagingException e) 
		{
			return "failed";
		}
		//send method is used to send mail from From address to To address.

	}
	
	public User changePassword(String email, String password) 
	{
		EntityManager manager = getManager() ;
		Query query = manager.createQuery("select u from User u where email =?1") ;
		query.setParameter(1, email) ;
		User user = (User) query.getSingleResult() ;
		
		User newUser = new User() ;
		
		
		if (user != null) 
		{
			newUser.setPassword(password);
			newUser.setId(user.getId());
			newUser.setAge(user.getAge());
			newUser.setBookings(user.getBookings());
			newUser.setEmail(user.getEmail());
			newUser.setGender(user.getGender());
			newUser.setName(user.getName());
			newUser.setPhone(user.getPhone());
			
			EntityTransaction transaction = manager.getTransaction() ;
			transaction.begin();
			manager.merge(newUser) ;
			transaction.commit();
			return newUser ;

			
		}
		return null ;
	}
	
	public String deleteUser(int id) 
	{
		EntityManager manager = getManager();
		User user = manager.find(User.class, id);
		
		if(user != null) {
			List<Booking> bookings = user.getBookings();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			if(bookings.isEmpty()) {
				manager.remove(user);
			}
			else {
				for (Booking booking : bookings) {
					
					List<Adventures> adventures = booking.getAdventures();
					manager.remove(booking);
					for (Adventures adventure :adventures) {
						manager.merge(adventure);
					}	
				}
				
				List<Booking> bookings2 = new ArrayList<Booking>();
				user.setBookings(bookings2);
				manager.merge(user);
				manager.remove(user);
			}
			transaction.commit();
			
			return "success";
		 }
		else {
			return "failed";
		}
	}
	
	public List<User> viewUserList() 
	{
		
		EntityManager manager = getManager();
		Query query = manager.createQuery("SELECT u FROM User u ");
		List<User> users = query.getResultList();
		
		return users ;
		
	}
	
	
	public String welcomeMail(String email, String name) 
	{	
		String from = "rushijadhavrj9566@gmail.com" ;
		String to = email ;
		
		Properties properties = System.getProperties() ;
		properties.put("mail.smtp.host", "smtp.gmail.com") ; 
		properties.put("mail.smtp.port", "465") ;
		properties.put("mail.smtp.ssl.enable", "true") ;
		properties.put("mail.smtp.auth", "true") ; 
		
		Session session = Session.getInstance(properties, new Mail()) ; 
		
		session.setDebug(true) ;
		
		try 
		{
			InternetAddress address = new InternetAddress(from);
			InternetAddress address2 = new InternetAddress(to) ;
			
			MimeMessage message = new MimeMessage(session) ;
			
			message.setFrom(address);
			message.addRecipient(RecipientType.TO, address2);
			message.setSubject("Welcome to Green Park");
			message.setText("Dear "+ name+"," + "\r\n"
							+ "\r\n"
							+ "Welcome to Green Park! We're thrilled to have you join our community of nature enthusiasts and adventurers. Get ready to embark on unforgettable outdoor experiences and discover stunning parks around the world.\r\n"
							+ "\r\n"
							+ "At Green Park, we're passionate about connecting people with nature and making park reservations seamless and enjoyable. Whether you're planning a family picnic, a solo hike, or a group camping trip, we've got you covered.\r\n"
							+ "\r\n" 
							+ "Warm regards, \r\n"
							+ "Green Park");
			
			Transport.send(message);
			return "success" ;
		} catch (MessagingException e) 
		{
			return "failed";
		}
	}
}
