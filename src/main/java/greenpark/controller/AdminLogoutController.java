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

import greenpark.dto.Admin;

@WebServlet("/adminLogout")
public class AdminLogoutController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession() ;
		session.invalidate(); 
		
		PrintWriter printWriter = resp.getWriter() ;
		printWriter.print("<h1 style=text-align:center> You are successfully logged out! </h1>");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp") ;
		dispatcher.include(req, resp) ;
		
		
	}
}
