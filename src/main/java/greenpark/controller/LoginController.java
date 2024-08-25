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

@WebServlet("/loginUser")
public class LoginController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String email = req.getParameter("email") ;
		String password = req.getParameter("password") ;
		
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		User user = new User() ;
		UserDao dao = new UserDao() ;
		
		try 
		{
			user = dao.login(email) ;
			if (user != null) 
			{
				if (user.getPassword().equalsIgnoreCase(password)) 
				{
					HttpSession session = req.getSession();
					session.setAttribute("user", user);
					printWriter.print("<h1>login  is done..!</h1>");
					dispatcher = req.getRequestDispatcher("UserHome.jsp") ;
					dispatcher.include(req, resp) ;
				}
				else
				{
					printWriter.print("<h1>invalid creadentials...!!</h1>") ;
					dispatcher = req.getRequestDispatcher("Login.html") ;
					dispatcher.include(req, resp) ;
				}
			}
			else
			{
				printWriter.print("<h1>invalid creadentials...!!</h1>") ;
				dispatcher = req.getRequestDispatcher("Login.html") ;
				dispatcher.include(req, resp) ;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
