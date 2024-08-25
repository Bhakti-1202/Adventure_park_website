<%@page import="greenpark.dto.User"%>
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
<title>Edit profile Page</title>
<style type="text/css">
    *
    {
        margin: 0;
        padding: 0;
        font-family: sans-serif;
    }

    body
    {
        background-color: #f0f5f5;
    }
   
    .container
    {
        height: 100%;
        width: 100%;
        position: fixed;
        top: 50px;
        left: 0px;
        display: flex;
        align-items: center;
        justify-content: center;   
    }

    .login-con
    {
        position: fixed;
        top: 140px;
        bottom: 30px;
        margin-left: 0;
        height: 350px;
        width: 700px;
        box-shadow: 0px 0px 10px 2px rgba(0, 0, 0, 0.5);
        border-radius: 30px;
        border: 2px solid;
        padding:10px 20px 20px 40px;
    }

    h2
    {
        font-size: 30px;
        text-align: center;
        color:black;
        margin-bottom: 20px;
    }

    .form-group 
    {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        margin-bottom: 20px;
    }

    .form-item 
    {
        width: 48%;
    }

    label 
    {
        display: block;
        margin-bottom: 5px;
        font-size: 20px;
    }

    input[type="text"],
    input[type="password"],
    input[type="number"],
    input[type="email"],
    input[type="password"],
    textarea,
    select 
    {
        width: 90%;
        padding: 10px;
        border: 1px solid #ced4da;
        border-radius: 10px;
        border: 1px solid ;
        outline: none;
        box-sizing: border-box;
        font-size: 16px;
        background: transparent; 
    }

    select 
    {   
        height: 40px;
    }

    button 
    {
        margin-left: 450px;
        margin-top: -50px;
        height: 20%;
        width: 20%;
        padding: 10px;
        border: none;
        border-radius: 30px;
        background-color: #007bff;
        color: black;
        font-size: 20px;
        font-weight: bolder;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button:hover 
    {
        background-color: palevioletred;
    }

    .input-field 
    {
        width: 30%;
        height: 20px;
        font-size: 15px;
        background: transparent; 
        padding-inline: 20px 50px;
        border-radius: 30px;
        outline: none;
    } 

</style>
</head>
<body>
	<%
		User user2 = (User) session.getAttribute("user") ;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("rushikesh") ;
		EntityManager manager = factory.createEntityManager() ;
		Query query = manager.createQuery("select u from User u where email = ?1") ;
		query.setParameter(1, user2.getEmail()) ;
		User user = (User) query.getSingleResult() ;		
	%>
	
	<div class="container">
           <div class="login-con">
             	<form action="editProfile" method="post">
                	<h2>Update Your Profile</h2>
            		<div class="form-group">
                		<div class="form-item">
                    		<label>Name:</label>
                    		<input type="text" value="<%= user.getName() %>" name="name" required>
                		</div>
                		<div class="form-item">
                    		<label>Email:</label>
                    		<input type="email" value="<%= user.getEmail() %>" name="email" required>
                		</div>
            		</div>
            		
            			<div class="form-group">
                			<div class="form-item">
                    		<label>Phone:</label>
                    		<input type="number" value="<%= user.getPhone() %>" name="phone" required>
                		</div>
	                	<div class="form-item">
	                    	<label>Age:</label>
	                    	<input type="number" value="<%= user.getAge() %>" name="age" required>
	                	</div>
            		</div>

		            <div class="form-group">
		                <div class="form-item">
		                    <label>Password:</label>
		                    <input type="password" value="<%= user.getPassword() %>" name="password" required>
		                </div>
		            </div>

		            <div class="form-group">
		                 <button type="submit">Update</button>
		            </div>
        		</form>
        
           </div>
</div>
</body>
</html>