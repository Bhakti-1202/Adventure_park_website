<%@page import="greenpark.dto.Admin"%>
<%@page import="java.util.Collections"%>
<%@page import="greenpark.dto.Booking"%>
<%@page import="java.util.List"%>
<%@page import="greenpark.dao.BookingDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Bookings</title>
<link rel="stylesheet" type="text/css" href="viewCss.css">
</head>
<body>
		<%
			Admin admin = (Admin) session.getAttribute("admin") ;
			if(admin != null)
			{
		%>
		<div>

	       <div class="nav-container">
          <a href="AdminHome.jsp"><h1>Green Park</h1></a>
          <ul>
            <li><a href="ViewUser.jsp"><button class="btn">View Users</button></a></li>
            <li><a href="ViewBookings.jsp"><button class="btn">View Bookings</button></a></li>
            <li><a href="ViewAdventures.jsp"><button class="btn">View Adventures</button></a></li>
            <li><a href="adminLogout"><button class="btn">Logout</button></a></li>
          </ul>
        </div>
	  </div>


	  <div>
			<table class="table-container">
				<tr>
					<th>Name</th>
					<th>No Of Members</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Date</th>
					<th>Amount </th>
				</tr>
				
		<%
			BookingDao dao = new BookingDao() ;
			List<Booking> bookings = dao.viewBookingList() ;
			Collections.reverse(bookings) ;
			for(Booking booking : bookings)
			{
		%>
		<tr>
					<td> <%= booking.getName() %> </td>
					<td> <%= booking.getNoOfMembers() %> </td>
					<td> <%= booking.getPhone() %> </td>
					<td> <%= booking.getEmail() %> </td>
					<td> <%= booking.getDate() %> </td>
					<td> <%= booking.getTotalCost() %> </td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		
		<%
			}
			else
			{	
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp") ;
				dispatcher.forward(request, response) ;
			}
		%>
			
</body>
</html>