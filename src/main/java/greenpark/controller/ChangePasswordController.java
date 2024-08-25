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

@WebServlet("/changePassword")
public class ChangePasswordController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String password1 = req.getParameter("password1") ;
		String password2 = req.getParameter("password2") ;
		
		HttpSession session = req.getSession() ;
		String email = (String)session.getAttribute("email") ;
		
		UserDao dao = new UserDao() ;
		User user ;
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		if (password1.equals(password2)) 
		{
			try 
			{
				user = dao.changePassword(email, password1) ;
				if (user != null) 
				{
					session.invalidate();
					printWriter.print("<h1> Password is changed. Now you can login...! </h1>");
					dispatcher = req.getRequestDispatcher("Login.html") ;
					dispatcher.include(req, resp) ;
				}
				else
				{
					session.invalidate();
					printWriter.print("<h1> Please try again...! </h1>");
					dispatcher = req.getRequestDispatcher("Login.html") ;
					dispatcher.include(req, resp) ;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else
		{
			printWriter.print("<h1> Password is not matched...! </h1>");
			dispatcher = req.getRequestDispatcher("ChangePassword.html") ;
			dispatcher.include(req, resp) ;
		}
	}
}
