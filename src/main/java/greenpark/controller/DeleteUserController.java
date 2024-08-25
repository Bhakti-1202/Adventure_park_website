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

@WebServlet("/deleteUser")
public class DeleteUserController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
//		int userId = Integer.parseInt(req.getParameter("userId")) ;
		
		HttpSession session = req.getSession() ;
		User user = (User) session.getAttribute("user") ;
		
		
		UserDao dao = new UserDao() ;
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		try {
			String result = dao.deleteUser(user.getId()) ;
			if (result.equalsIgnoreCase("success")) 
			{
				session.invalidate();
				writer.print("<h1> Account is deleted..! </h1>");
				dispatcher = req.getRequestDispatcher("index.jsp") ;
				dispatcher.include(req, resp) ;
			} else if (result.equalsIgnoreCase("failed")) 
			{
				writer.print("<h1> Account id not deleted...! </h1>") ;
				dispatcher = req.getRequestDispatcher("Profile.jsp") ;
				dispatcher.include(req, resp) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
