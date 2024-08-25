package greenpark.controller.booking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.BookingDao;
import greenpark.dto.Booking;

@WebServlet("/deleteBooking")
public class DeleteBookingController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int id = Integer.parseInt(req.getParameter("id")) ;
		
		BookingDao dao = new BookingDao() ;
		
		PrintWriter writer = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		
		Booking booking ; 
		
		try {
			booking = dao.deleteBooking(id) ;
			if (booking != null) 
			{
				writer.print("Booking is deleted..!");
				dispatcher = req.getRequestDispatcher("ViewBookings.jsp") ;
				dispatcher.include(req, resp);
			}
			else
			{
				writer.print("Booking is not deleted..!");
				dispatcher = req.getRequestDispatcher("ViewBookings.jsp") ;
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
