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
				<form action="login" method="post">
					<ul>
						<li>
							<label>User Name</label><br/>
							<input type="text" name="userName" />	
						</li>
						<li>
							<label>Password</label><br/>
							<input type="password" name="password" />	
						</li>
						<li>
							<input class="login_button" type="submit" name="Login!" value="Login!">					
						</li>
					</ul>
				</form>
				
				<div class="no_account">
					<a href="home">Home</a>
					<a href="register_user.jsp">Register an Account</a>
				</div>
			</div>
			
			<div class="description">
				<h2>Welcome to COOL_NAME</h2>
				<p>
					This is the login page. It is different than the index page.
					Some text Some textSome text Some text Some text Some text 
					Some text 
				</p>	
			</div>	
		</div>
		
		<br />
		<p>DEBUG: login_user.jsp</p>
	</body>
</html>