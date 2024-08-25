package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import greenpark.dao.UserDao;
import greenpark.dto.User;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

@WebServlet("/forgotPassword")
public class ForgotPasswordController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		String email = req.getParameter("to") ;
		
		HttpSession session = req.getSession() ;
		session.setAttribute("email", email);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("rushikesh") ;
		EntityManager manager = factory.createEntityManager() ;
		Query query = manager.createQuery("select u from User u where email=?1") ;
		query.setParameter(1, email) ;
		User dbUser = new User() ;
		try 
		{
			dbUser = (User)query.getSingleResult() ;
		} catch (NoResultException e) {
			dbUser= null ;
		}
		
		UserDao dao = new UserDao() ;
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		if (dbUser != null) 
		{
			String result =	dao.forgotPassword(email);
			if (result.equalsIgnoreCase("success")) 
			{
				writer.print("<h1>Otp will be send on your email..!</h1>");
				dispatcher = req.getRequestDispatcher("ForgotPassword.html") ;
				dispatcher.include(req, resp) ;
			}
			else if(result.equalsIgnoreCase("failed"))
			{
				writer.print("<h1>Please Try again </h1>");
				dispatcher = req.getRequestDispatcher("ForgotPassword.html") ;
				dispatcher.include(req, resp) ;
			}
		}
		else
		{
			writer.print("<h1>Given email is not registered. Please Register first..!</h1>");
			dispatcher = req.getRequestDispatcher("ForgotPassword.html") ;
			dispatcher.include(req, resp) ;
		}
	}
}
