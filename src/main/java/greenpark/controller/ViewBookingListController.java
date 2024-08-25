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

import greenpark.dao.BookingDao;
import greenpark.dto.Booking;

public class ViewBookingListController extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		BookingDao bookingDao = new BookingDao();
		List<Booking> bookings = new ArrayList<Booking>();
		
		PrintWriter printWriter = resp.getWriter();
		RequestDispatcher dispatcher ;	
		try {
			bookings = bookingDao.viewBookingList();
			if (bookings.isEmpty()) 
			{
				printWriter.print("<h1>No Data to show at the moment please try again later ..!</h1>");
				dispatcher = req.getRequestDispatcher("Profile.jsp") ;
				dispatcher.include(req, resp) ;
			}
			else {
				req.setAttribute("listOfBooking", bookings);
				dispatcher = req.getRequestDispatcher("Profile.jsp") ;
				dispatcher.include(req, resp) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
