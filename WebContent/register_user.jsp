<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.controllers.*" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

		<link href="css/homePage.css" rel="stylesheet" type="text/css" />
		<link href="css/style.css" rel="stylesheet" type="text/css" />
	
		<!-- ADD GENERAL HEAD FILE -->
		<jsp:include page="_general_head_info.jsp" />
		
		<title>Quizbook - Register User</title>
	</head>
	<body>
		<div class="main">
		<!--  INCLUDE HEADER -->
		<jsp:include page="_header.jsp"/>
		
		<!-- MAIN CONTENT START -->		
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
				<h2>Create an account</h2>
				<form action="register" method="post">
					<ul>
						<li>
							<label>User Name</label><br/>
							<input type="text" name="userName" />	
						</li>
						<li>
							<label>Email</label>
							<input type="text" name="email" />	
						</li>
						<li>
							<label>Password</label><br/>
							<input type="password" name="password" />	
						</li>
						<li>
							<label>Confirm Password</label><br />
							<input type="password" name="passwordConfirm" />
						</li>	
						<li>
							<input class="login_button" type="submit" name="Create" value="Create">					
						</li>
					</ul>
				</form>
				<div class="no_account">
					<a href="index.jsp">Home</a>
				</div>
			</div>
		
			<div class="description">
				<h2>Welcome to QUIZ BOOK! </h2>
				<p>
								</p>
			</div>
		</div>
		<br />
	</body>
</html>
