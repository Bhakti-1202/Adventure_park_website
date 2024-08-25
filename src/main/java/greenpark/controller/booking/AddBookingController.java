package greenpark.controller.booking;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import greenpark.dao.BookingDao;
import greenpark.dto.Booking;

@WebServlet("/addBooking")
public class AddBookingController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name = req.getParameter("name") ;
		int members = Integer.parseInt(req.getParameter("members")) ;
		long phone = Long.parseLong(req.getParameter("phone")) ;
		String email = req.getParameter("email") ;
		String date = req.getParameter("date") ;
		
		List<String> list = new ArrayList<String>() ;
		for(int i=1; i<=20; i++)
		{
			String s = Integer.toString(i) ;
			String adv = req.getParameter(s) ;
			if (adv != null) 
			{
				list.add(adv) ;
			}
			
			i = Integer.parseInt(s) ;
		}
		Booking booking = new Booking() ;
		booking.setName(name);
		booking.setNoOfMembers(members);
		booking.setEmail(email);
		booking.setPhone(phone);
		booking.setDate(date);
		
		
		BookingDao dao = new BookingDao() ;
		System.out.println(dao.gettotalCost(members, list));
		booking.setTotalCost(dao.gettotalCost(members, list));
		Booking dbBooking = dao.book(booking, list, email) ;
		if (dbBooking != null) 
		{
			RequestDispatcher dispatcher = req.getRequestDispatcher("ViewBookings.jsp") ;
			dispatcher.forward(req, resp);
		}
	}
}
