package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.UserDao;
import greenpark.dto.User;

@WebServlet("/registerUser")
public class UserController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name = req.getParameter("name") ;
		String email = req.getParameter("email") ;
		Long phone = Long.parseLong(req.getParameter("phone")) ;
		String password = req.getParameter("password") ;
		int age = Integer.parseInt(req.getParameter("age")) ;
		String gender = req.getParameter("gender") ;
		
		User user = new User() ;
		user.setName(name);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setAge(age);
		user.setGender(gender);
		
		UserDao dao = new UserDao() ;
		User dbUser = new User() ;
		
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		try 
		{
			dbUser = dao.register(user) ;
			if (dbUser != null) 
			{
				writer.print("<h1>Regiseration is done..!</h1>");
				dispatcher = req.getRequestDispatcher("Login.html") ;
				dispatcher.include(req, resp);
				String result = dao.welcomeMail(email, name) ;
				if (result.equalsIgnoreCase("SUCCESS")) 
				{
					System.out.println("Mail send successfully");
				}
				else if (result.equalsIgnoreCase("failed")) 
				{
					System.out.println("Mail not send");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
