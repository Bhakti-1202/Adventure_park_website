<%@page import="java.util.Collections"%>
<%@page import="greenpark.dto.Adventures"%>
<%@page import="greenpark.dto.Booking"%>
<%@page import="java.util.List"%>
<%@page import="greenpark.dto.User"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile page</title>
<link rel="stylesheet" href="profile.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body style="background-color: #4a4e69;">
		<div class="green_mdiv">
			<div class="navigation_bar">
            
            <nav class="navbar navbar-expand-lg navbar-light ">
               <img src="Images/leaf.png" alt="" height="45px">
              <a class="navbar-brand" href="#"><span class="logo">Green park</span></a>
                  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                  </button>

                  <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                      <li class="nav-item active">
                        <a class="nav-link" href="UserHome.jsp"><span class="navmenu">Home</span> <span class="sr-only">(current)</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="Book.jsp"><span class="navmenu">Booking</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="Profile.jsp"><span class="navmenu">Profile</span></a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" href="logout"><span class="navmenu">Logout</span></a>
                      </li>
                      
                     
                    </ul>
                  </div>
				</nav>
        	</div>
		</div>
		
		<%
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("bhakti") ;
			EntityManager manager = factory.createEntityManager() ;
			
			User user2 = (User) session.getAttribute("user") ;
			
			if(user2 != null)
			{
			
			Query query = manager.createQuery("select u from User u where email=?1") ;
			query.setParameter(1, user2.getEmail()) ;
			User user = (User) query.getSingleResult() ;
		%>
		
		
		<div class="main-container">
			<div class="image-container">
		    <img src="Images/User.png">
		    <h3><%= user.getName() %></h3>
		    <a href="EditProfile.jsp"><button >Edit</button></a> <br><br>
		    <a href="deleteUser"><button>Delete</button></a> <br><br>
		    <a href="logout"><button>Logout</button></a>
		  </div>
		  
		  <div class="about-container">
  
		    <div class="profile-container">
		      <h2>About </h2>
		      <label>Full Name: </label> <span><%= user.getName() %></span> <br> <hr>
		      <label>Email: </label> <span><%= user.getEmail() %></span> <br> <hr>
		      <label>Phone: </label> <span><%= user.getPhone() %></span> <br> <hr>
		      <label>Age: </label> <span><%= user.getAge() %></span> <br> <hr>
		      <label>Gender: </label> <span><%= user.getGender() %></span> <br> <hr>
		    </div>
			<div class="allBookings">
				<div class="heading">
					<h2> Recent Bookings </h2>
				</div>
				
				<%
			    	List<Booking> bookings = user.getBookings();
					Collections.reverse(bookings) ;
			    	for(Booking book: bookings)
			    	{
		    	%>
			   	<div class="booking-container">
			      <label>Full Name: </label> <span><%= book.getName() %></span> <br> <hr>
			      <label>No of Members:  </label> <span><%= book.getNoOfMembers() %></span> <br> <hr>
			      <label>Phone:  </label> <span><%= book.getPhone() %></span> <br> <hr>
			      <label>Booking Date:  </label> <span><%= book.getDate() %></span> <br> <hr>
			      <label>Adventures: </label> 
			      <%
			      	List<Adventures> adventures = book.getAdventures() ;
			      	for(Adventures adv : adventures)
			      	{
			      %>
			      <span><%= adv.getAdventures() %>,</span>
			      
			      <%
			      	}
			      %>
			     <br> <hr>
			     <label>Total Cost:  </label> <span><%= book.getTotalCost()%>Rs</span> <br> <hr>
			      <form action="cancelBooking" method="post">
					<input type="hidden" name="bookingId" value="<%= book.getId()%>" >
					<input type="hidden" name="userId" value="<%= user.getId() %>" >
					<button type="submit">Cancel</button>
				</form>
			    </div>
			    
			    <% 
			    	}
			    %>
	    	</div>
    
		</div>
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