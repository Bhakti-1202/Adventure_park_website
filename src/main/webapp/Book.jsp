<%@page import="greenpark.dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Page</title>
<link rel="stylesheet" href="book.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

</head>
<body style="background-color: #4a4e69;">

		<%
			User user  = (User) session.getAttribute("user") ;
			if(user != null)
			{
		%>

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
		
		
    <div class="container">
        <h1>Book Your Favorite Adventures</h1>
        <form action="bookAdventures" method="post">
            <label>Number of Members:</label>
            <input type="number" name="members" placeholder="Number of Members" class="input-field" id="mem" required min="1" max="100">
            <label>Date:</label>
            <input type="date" name="date" id="date"  class="input-field" required >
            <label>Select Adventures:</label>
            <input type="checkbox" name="1" value="1"> Camping<br>
            <input type="checkbox" name="2" value="2">Hill Climbing<br>
            <input type="checkbox" name="3" value="3">Rock Climbing<br>
            <input type="checkbox" name="4" value="4">Zip lining<br>
            <input type="checkbox" name="5" value="5">Sky Diving<br>
            <input type="checkbox" name="6" value="6">Bungee Jumping<br>
            <input type="checkbox" name="7" value="7">Underwater jumping<br>
            <input type="checkbox" name="8" value="8">Boating
            
            <br><br>
            <button type="submit" class="submit" id="btn">Book</button>
        </form>
    </div>
    
    <script type="text/javascript">
    		
		    let date = new Date() ;
			const month = date.getMonth()+1 ;
			const currentDate = `${date.getFullYear()}-${(month<10)?"0"+month:month}-${date.getDate()}` ;
			const inputDate = document.getElementById("date") ;
			inputDate.value = currentDate ;
			inputDate.min = currentDate ;
			
			const maxDate = `${date.getFullYear()+1}-${(month<10)?"0"+month:month}-${date.getDate()}` ;
			inputDate.max = maxDate ;
    		
			
			
			
			
    		
			let totalCost = 0 ;
			const prices = [8000,800,600,800,3000,1500,3000,500] ;
			const btn = document.querySelector('#btn') ;
			btn.addEventListener('click', (event) => {
				let checkboxes = document.querySelectorAll('input[type="checkbox"]:checked') ;
				let values = [] ;
				checkboxes.forEach((ele)=>{
					values.push(ele.value) ;
				}) ;
				
				values.forEach((ele)=>{
					totalCost += prices[ele-1] ;
				}) ;
				
				alert("Total Price is: "+ (totalCost*members)) ;
			}) ;
			
			
		</script>
		
		
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