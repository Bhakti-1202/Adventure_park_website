package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.AdminDao;
import greenpark.dto.Admin;

public class DeleteAdminController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int adminId = Integer.parseInt(req.getParameter("adminId"));
		

		AdminDao adminDao = new AdminDao();
		PrintWriter printWriter = resp.getWriter();
		RequestDispatcher dispatcher ;
		
		try {
			Admin result = adminDao.deleteAdmin(adminId);
			if (result != null) 
			{
				printWriter.print("<h1>Admin account deleted successfully successfully..!</h1>");
				dispatcher = req.getRequestDispatcher("index.jsp") ;
				dispatcher.include(req, resp) ;
			}
			else {
				printWriter.print("<h1>Failed to delete account please try again ..!</h1>");
				dispatcher = req.getRequestDispatcher("AdminHomrPage.jsp") ;
				dispatcher.include(req, resp) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
