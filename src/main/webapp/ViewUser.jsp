<%@page import="greenpark.dto.Admin"%>
<%@page import="java.util.Collections"%>
<%@page import="greenpark.dto.User"%>
<%@page import="java.util.List"%>
<%@page import="greenpark.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View User</title>
<link rel="stylesheet" type="text/css" href="viewCss.css">
</head>
<body>
		<%
			Admin admin = (Admin) session.getAttribute("admin") ;
			if(admin != null)
			{
		%>
		      <div class="nav-container">
		          <a href="AdminHome.jsp"><h1>Green Park</h1></a>
		          <ul>
		            <li><a href="ViewUser.jsp"><button class="btn">View Users</button></a></li>
		            <li><a href="ViewBookings.jsp"><button class="btn">View Bookings</button></a></li>
		            <li><a href="ViewAdventures.jsp"><button class="btn">View Adventures</button></a></li>
		            <li><a href="adminLogout"><button class="btn">Logout</button></a></li>
		          </ul>
		        </div>
		  	
			<table class="table-container">
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Age</th>
					<th>Gender</th>
					<th>Password</th>
				</tr>
	  
	<%
		UserDao dao = new UserDao() ;
		List<User> users = dao.viewUserList() ;
		Collections.reverse(users) ;
		for(User user : users)
		{
	%>
				<tr>
					<td> <%= user.getName() %> </td>
					<td> <%= user.getEmail() %> </td>
					<td> <%= user.getPhone() %> </td>
					<td> <%= user.getAge() %> </td>
					<td> <%= user.getGender() %> </td>
					<td> <%= user.getPassword() %> </td>
				</tr>
			<%
				}	
			%>
			</table>
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