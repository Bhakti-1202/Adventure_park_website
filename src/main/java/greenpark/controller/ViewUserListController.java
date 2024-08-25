package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.UserDao;
import greenpark.dto.User;

public class ViewUserListController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDao userDao = new UserDao();
		List<User> users = new ArrayList<User>();
		
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		try {
			users = userDao.viewUserList();
			if (users.isEmpty()) 
			{
				printWriter.print("<h1>Failed to retrive the data please try again </h1>");
				dispatcher = req.getRequestDispatcher("AdminHomePage.jsp") ;
				dispatcher.include(req, resp) ;
				
			}
			else {	
				req.setAttribute("listOfUsers", users);
				dispatcher = req.getRequestDispatcher("AdminHomePage.jsp") ;
				dispatcher.include(req, resp) ; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
