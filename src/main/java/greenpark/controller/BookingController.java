package greenpark.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import greenpark.dao.BookingDao;
import greenpark.dto.Booking;
import greenpark.dto.User;


@WebServlet("/bookAdventures")
public class BookingController extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		int members = Integer.parseInt(req.getParameter("members")) ;
		String date = req.getParameter("date") ;
		
		HttpSession session = req.getSession() ;
		User user = (User) session.getAttribute("user") ;
		
		List<String> list = new ArrayList<String>() ;
		
		for(int i=1;i<=10; i++)
		{
			String s = Integer.toString(i) ;
			String adv = req.getParameter(s) ;
			if(adv != null)
			{
				list.add(adv) ;
			}
			i = Integer.parseInt(s) ;
		}
		
		Booking booking = new Booking() ;
		booking.setName(user.getName());
		booking.setNoOfMembers(members);
		booking.setPhone(user.getPhone());
		booking.setEmail(user.getEmail());
		booking.setDate(date);
		
		BookingDao dao = new BookingDao() ;
		booking.setTotalCost(dao.gettotalCost(members, list));
		Booking dbBooking = dao.book(booking, list, user.getEmail());
		
		PrintWriter printWriter = resp.getWriter() ;
		RequestDispatcher dispatcher ;
		if (dbBooking != null) 
		{
			printWriter.print("<h1>Booking is Done..!</h1>");
			dispatcher = req.getRequestDispatcher("UserHome.jsp") ;
			dispatcher.include(req, resp);
		}
	}
}
