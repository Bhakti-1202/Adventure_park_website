package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.UserDao;

@WebServlet("/verifyOtp")
public class VerifyOtpController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int inputOtp = Integer.parseInt(req.getParameter("otp")) ;
		
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		if (inputOtp == UserDao.otp) 
		{
			writer.print("<h1> Otp is correct</h1>");
			dispatcher = req.getRequestDispatcher("ChangePassword.html") ;
			dispatcher.include(req, resp) ;
		}
		else
		{
			writer.print("<h1>otp is not correct</h1>") ;
			dispatcher = req.getRequestDispatcher("ForgotPassword.html") ;
			dispatcher.include(req, resp) ;
		}
		
		
	}
}
