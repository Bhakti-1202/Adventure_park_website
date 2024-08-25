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

import greenpark.dao.AdminDao;
import greenpark.dto.Admin;

@WebServlet("/loginAdmin")
public class AdminLoginController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		AdminDao dao = new AdminDao();
		Admin admin ;
		 try 
		 {
			 admin =dao.adminLogin(email);
			 if(admin.getPassword().equals(password)) 
			 {
				 HttpSession session = req.getSession() ;
				 session.setAttribute("admin", admin);
//				 printWriter.print("<h1>login  is done..!</h1>");
				dispatcher = req.getRequestDispatcher("AdminHome.jsp") ;
				dispatcher.include(req, resp) ;
			 }
			 else 
			 {
				printWriter.print("<h1>invalid creadentials</h1>");
				dispatcher = req.getRequestDispatcher("AdminSignInUp.html") ;
				dispatcher.include(req, resp) ;
			 }
			 
		 }
		 catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
