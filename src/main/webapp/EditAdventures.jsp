<%@page import="greenpark.dto.Admin"%>
<%@page import="greenpark.dto.Adventures"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Adventure</title>
<style type="text/css">

		body
		{
			background-image: url("Images/background1.png");
			background-size: cover;
		}
		.container
		{
			margin-top: 80px;
			margin-left: 60px;
			background-color: navajowhite;
			width: 40%;
			height: 420px;
			border: 0px 0px 20px gray;
			border-radius: 20px;
		}

		.container h1
		{
			text-align: center;
			padding-top: 20px;
			text-decoration: underline;
		}

		.lable-field
		{
			margin-left: 50px;
			font-size: 20px;
			font-weight: 400;
		}

		.container input
		{
			margin-left: 50px;
		}

		.input-field
		{
			margin-top: 5px;
			margin-left: 50px;
			width: 50%;
			height: 25px;
			border-radius: 10px;
			border: 1px solid gray;
			outline: none;
			text-align: center;
			font-size: 20px;
		}

		.container button
		{
			margin-top: 10px ;
			width: 150px;
			height: 40px;
			border-radius: 10px;
			outline: none;
			border: 1px solid gray;
			font-size: 17px;
			margin-left: 60px;
			cursor: pointer;

		}

		.container button:hover
		{
			background-color: red;
			border: 1px solid white;
		}

		.container a
		{
			text-decoration: none;
			font-size: 17px;
			color: black;
		}
	</style>
</head>
<body>

	<%
		Admin admin = (Admin) session.getAttribute("admin") ;
		if(admin != null)
		{
			int id = Integer.parseInt(request.getParameter("id")) ;
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("rushikesh") ;
			EntityManager manager = factory.createEntityManager() ;
			Query query = manager.createQuery("select a from Adventures a where id=?1") ;
			query.setParameter(1, id) ;
			Adventures adventures = (Adventures) query.getSingleResult() ;
		
	%>

	<div class="container">
			<h1> Edit Adventure </h1>
			<form action="editAdventure" method="post">
				<input type="hidden" name="id" value="<%= id %>">
				<label class="lable-field">Adventure Name: </label> <br>
				<input type="text" name="name" class="input-field" required value="<%= adventures.getAdventures() %>"> <br><br>
				<label class="lable-field">Adventure Price: </label> <br>
				<input type="number" name="price" class="input-field" required value="<%= adventures.getPrice() %>"> <br><br>
				<button type="submit"> Update Adventure </button> <br>
				<button> <a href="ViewAdventures.jsp"> Cancel </a> </button>
			</form>
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