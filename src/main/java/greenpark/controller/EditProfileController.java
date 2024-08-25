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

@WebServlet("/editProfile")
public class EditProfileController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name = req.getParameter("name") ;
		String email = req.getParameter("email") ;
		long phone = Long.parseLong(req.getParameter("phone")) ;
		int age = Integer.parseInt(req.getParameter("age")) ;
		String password = req.getParameter("password") ;
		
		User user = new User() ;
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAge(age);
		user.setPassword(password);
		
		HttpSession session = req.getSession() ;
		User user2 = (User) session.getAttribute("user") ;
		
		UserDao dao = new UserDao() ;		
		User dbUser = dao.editProfile(user, user2.getEmail()) ;
		
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		if (dbUser != null) 
		{
			session.setAttribute("email", dbUser.getEmail());
			writer.print("<h1>Profile updated successfully..!</h1>");
			dispatcher = req.getRequestDispatcher("Profile.jsp") ;
			dispatcher.include(req, resp) ;
		}
	}
}
