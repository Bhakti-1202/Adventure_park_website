package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.BookingDao;
import greenpark.dto.User;

@WebServlet("/cancelBooking")
public class CancelBooking extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int userId = Integer.parseInt(req.getParameter("userId")) ;
		int bookingId = Integer.parseInt(req.getParameter("bookingId")) ;
		System.out.println(userId);
		System.out.println(bookingId);
		
		BookingDao dao = new BookingDao();
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		try {
			String result = dao.cancelBooking(userId, bookingId) ;
			if(result.equalsIgnoreCase("success"))
			{
				System.out.println(result);
				writer.print("<h1>Booking is cancelled..!</h1>");
				dispatcher = req.getRequestDispatcher("Profile.jsp") ;
				dispatcher.include(req, resp) ;
			}
			else if (result.equalsIgnoreCase("failed"))
			{
				System.out.println(result);
				writer.print("<h1>Booking is not cancelled..!</h1>");
				dispatcher = req.getRequestDispatcher("Profile.jsp") ;
				dispatcher.include(req, resp) ;
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
