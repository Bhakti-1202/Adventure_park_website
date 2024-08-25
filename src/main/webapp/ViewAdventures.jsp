<%@page import="greenpark.dto.Admin"%>
<%@page import="java.util.Collections"%>
<%@page import="greenpark.dto.Adventures"%>
<%@page import="java.util.List"%>
<%@page import="greenpark.dao.AdventureDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Adventures</title>
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

	  <div class="btn-container">
	  	<button> <a href="AddAdventures.html"> Add Adventures </a> </button>
	  </div>


	  <div>
			<table class="table-container">
				<tr>
					<th>Adventure</th>
					<th>Price</th>
					<th>Action</th>
					<th>Action</th>
				</tr>
	<%
		AdventureDao dao = new AdventureDao() ;
		List<Adventures> adventures1 = dao.viewAdventures() ;
		Collections.reverse(adventures1) ;
		for(Adventures adv : adventures1)
		{
	%>
		<tr>
			<td><%= adv.getAdventures() %></td>
			<td><%= adv.getPrice() %></td>
			<td><a href="EditAdventures.jsp?id=<%= adv.getId()%>">Edit</a></td>
			<td><form action="deleteAdventure" method="post"><input type="hidden" name="id" value="<%= adv.getId() %>"> <button type="submit" class="btn">Delete</button></form></td>
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