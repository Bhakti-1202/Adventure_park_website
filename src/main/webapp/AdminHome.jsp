<%@page import="java.io.PrintWriter"%>
<%@page import="greenpark.dto.User"%>
<%@page import="greenpark.dto.Adventures"%>
<%@page import="java.util.Collections"%>
<%@page import="greenpark.dto.Booking"%>
<%@page import="java.util.List"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="greenpark.dto.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<link rel="stylesheet" href="adminHome.css">
</head>
<body>
	<div class="container">
        
        <div class="nav-container">
          <a href="AdminHome.jsp"><h1>Green Park</h1></a>
          <ul>
            <li><a href="ViewUser.jsp"><button class="btn">View Users</button></a></li>
            <li><a href="ViewBookings.jsp"><button class="btn">View Bookings</button></a></li>
            <li><a href="ViewAdventures.jsp"><button class="btn">View Adventures</button></a></li>
            <li><a href="adminLogout"><button class="btn">Logout</button></a></li>
          </ul>
        </div>
		
		<%
			Admin admin = (Admin) session.getAttribute("admin") ;	
			if(admin != null)
			{
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("bhakti") ;
			EntityManager manager = factory.createEntityManager() ;
			
			Query query = manager.createQuery("select count(*) from User") ;
			long userCount = (Long)query.getSingleResult() ;
			
			Query query2 = manager.createQuery("select count(*) from Booking") ;
			long bookingCount = (Long) query2.getSingleResult() ;
			
			Query query3 = manager.createQuery("select count(*) from Adventures") ;
			long countAdventures = (Long) query3.getSingleResult() ;
			
			Query query4 = manager.createQuery("select sum(totalCost) from Booking") ;
			double income = (Double) query4.getSingleResult() ;
		%>

        <div class="container1">
          <div class="small-con">
              <div class="text-con">
                  <h3><%= userCount %></h3>
                  <p>Users</p>
              </div>

              <div class="img-con">
                 <img src="Images/Users.png" height="50px">
              </div>
          </div>

          <div class="small-con">
              <div class="text-con">
                  <h3><%= bookingCount %></h3>
                  <p>Bookings</p>
              </div>

              <div class="img-con">
                 <img src="Images/book.png" height="50px" style="margin-left: 20px;">
              </div>
            
          </div>

          <div class="small-con">
              <div class="text-con">
                  <h3><%= countAdventures %></h3>
                  <p>Adventures</p>
              </div>

              <div class="img-con">
                 <img src="Images/Adventures.png" height="50px" style="margin-left: 30px;">
              </div>
            
          </div>

          <div class="small-con1">
              <div class="text-con1">
                  <h3>Rs <span><%= income %></span></h3>
                  <p>Income</p>
              </div>

              <div class="img-con1">
                 <img src="Images/Income.png" height="50px" style="margin-left: 30px;">
              </div>
            
          </div>
        </div>

        <!-- first container ends  -->

        <!-- second container starts -->
        <div class="mainContainer2">

          <div class="container2">


                      <div class="heading-div">
                        <h1> Recent Bookings </h1>
                        <a href="ViewBookings.jsp"><button>See all</button></a>
                      </div>

                      <hr>

                      <div class="booking-container">
                        <table>
                          <tr>
                            <th>Name</th>
                            <th>Date</th>
                            <th>Amount</th>
                            <th>Adventures</th>
                          
                          <%
	                      	Query query5 = manager.createQuery("select b from Booking b") ;
	                      	List<Booking> bookings = query5.getResultList() ;
	                      	Collections.reverse(bookings) ;
	                      	for(int i=0; i<bookings.size(); i++)
	                      	{
	                      		if(i==10)
	                      			break ;
	                      %>
                           <tr>
                            <td><%= bookings.get(i).getName() %></td>
                            <td><%= bookings.get(i).getDate() %></td>
                            <td><%= bookings.get(i).getTotalCost() %></td>
                            <td>
                            <%
                            	List<Adventures> adventures = bookings.get(i).getAdventures() ;
                            
                            	for(Adventures adv : adventures)
                            	{
                            %>
                            		<span><%= adv.getAdventures()  %>, </span>
                            <%
                            	}
                            %>
                            </td>
                          </tr>

						<%
	                      	}
						%>
                        </table>
                      </div>

          </div>

          <div class="container3">
              <div class="heading-container">
                <h1> New Users </h1>
                <a href="ViewUser.jsp"><button>See all</button></a>
              </div>
              <hr>
              
              <%
              	Query query6 = manager.createQuery("select u from User u") ;
              	List<User> users = query6.getResultList() ;
              	Collections.reverse(users) ;
              	for(int i=0; i<users.size(); i++)
              	{
              		if(i==7)
              			break ;
      
              %>

              <div class="user-container">
                <div style="display: flex; width: 100%; background-color: whitesmoke;">
                  <img src="Images/User.png" height="40px" style="margin-left: 20px; margin-top: 5px;"> 
                  <h3 style="margin-left: 40px; margin-top:10px"><%= users.get(i).getName() %></h3>
                </div>
                <hr>
                
               <%
                }
               %>

                 
              </div>
          </div>

        </div>
        <!-- second container ends -->
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