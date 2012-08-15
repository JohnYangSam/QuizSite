<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.controllers.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/homePage.css" rel="stylesheet" type="text/css" />

		<title>Quizbook - Login</title>
	</head>
	<body>
		<div class="main">
		
			<!-- FAILURE MESSAGE goes here is the user entered bad input -->
			<div class="error" id="failureMessage">
				<% 
				String failureMessage = (String)request.getAttribute("failureMessage");
				if(failureMessage == null) {
					out.println("<h2> Sign up to start quizzing! </h2>");
				} else {
					out.println("<h2>" + failureMessage + "</h2>");	
				}	
				%>
			</div>
			<div class="login">
				<h2>Login</h2>
				<form action="logout" method="post">
					<input class="" type="submit" name="Logout" value="Logout?" />
					<a href="home"><button class="" type="button" name="Home" value="home">Home</button></a>			
				</form>
				
			</div>
			
			<div class="description">
				<h2>This is the logout text!</h2>
				<p>
					This is the logout page. It is different than the index page.
					Some text Some textSome text Some text Some text Some text 
					Some text 
				</p>	
			</div>	
		</div>
		<br />
		<p>DEBUG: logout_user.jsp</p>
	</body>
</html>