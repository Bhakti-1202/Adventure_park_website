package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.AdminDao;
import greenpark.dto.Admin;

@WebServlet("/registerAdmin")
public class AdminRegisterController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setPassword(password);
		
		AdminDao adminDao = new AdminDao();
		Admin admindb = new Admin();
		
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		try {
			admindb = adminDao.regiterAdmin(admin);
			if (admindb != null) 
			{
				printWriter.print("<h1>Regiseration is done..!</h1>");
				dispatcher = req.getRequestDispatcher("AdminSignInUp.html") ;
				dispatcher.include(req, resp) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
