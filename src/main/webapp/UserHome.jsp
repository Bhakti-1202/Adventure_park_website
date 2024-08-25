<%@page import="greenpark.dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<title>User Page</title>
<link rel="stylesheet" href="userHome.css">
</head>
<body>
		<%
			User user = (User) session.getAttribute("user") ;
			if(user != null)
			{
		%>
		
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
        <div>
          <video autoplay muted loop="infinte"  height="100%" width="100%" align="center">
              <source src="Images/userPage.mp4">
          </video>
      </div>
      <h3 id="advanture-name">Top Adventures</h3> 
      <div class="advanture-main">
      
        <div class="advanture-submain">
          <div class="adventure-section" id="camp">
          </div>
          <div><h4>Camping</h4></div>

          <a href="Book.jsp"><button class ="book">Book</button></a>
        </div>
        <div class="advanture-submain">
          <div class="adventure-section" id="boat"></div>
          <div><h4>Boating</h4></div>
          <a href="Book.jsp"><button class ="book">Book</button></a>
        </div>
        <div class="advanture-submain">
          <div class="adventure-section" id="jump"></div>
          <div><h4>Bungee Jumping</h4></div>
          <a href="Book.jsp"><button class ="book">Book</button></a>
        </div>
       

      </div>
      <div class="advanture-main">
        <div class="advanture-submain">
            <div class="adventure-section" id="zip"> </div>
            <div><h4>Zip-lining</h4></div>
            <a href="Book.jsp"><button class ="book">Book</button></a>
          </div>
        <div class="advanture-submain">
          <div class="adventure-section" id="sky"></div>
          <div><h4>Sky Diving</h4></div>
          <a href="Book.jsp"><button class ="book">Book</button></a>
        </div>
        <div class="advanture-submain">
          <div class="adventure-section" id="rock"></div>
          <div><h4>Rock Climbing</h4></div>
          <a href="Book.jsp"><button class ="book">Book</button></a>
        </div>
      </div>
      
      
      <footer>
        <div><a href="home.html"> GREENPARK: </a>India's Favourite Themed Entertainment Destination
       </div>
         Made with &hearts; By Green park <br>
         <div class="img">
         <img src="Images/green.png" alt="" height="200px" width="200px">
         </div>
         &#8364; 2001-2023,greenpark.com,Inc.or its affiliates
       
       </footer>
       
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