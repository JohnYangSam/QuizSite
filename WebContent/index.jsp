<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*,quizsite.controllers.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/homePage.css" rel="stylesheet" type="text/css" />
	
		<!-- ADD GENERAL HEAD FILE -->
		<jsp:include page="_general_head_info.jsp" />
			
		<title>Quizbook - Overview</title>
	</head>


	<body>
		<!--  INCLUDE HEADER -->
		<jsp:include page="_header.jsp"/>
		
		<!-- MAIN CONTENT START -->	
		<div class="main">
			<div class="login">
				<h2>Have an account?</h2>
				<form action="login" method="post">
					<ul>
						<li>
							<label>Login </label><br/>
							<input type="text" name="userName" />	
						</li>
						<li>
							<label>Password</label><br/>
							<input type="password" name="password" />	
						</li>
						<li>
							<input class="login_button" type="submit" value="Go!">					
						</li>
					</ul>
				</form>
				
				<div class="no_account">
					<span>Don't have an account yet? No problems!</span><br/>
					<a href="<%= Util.REGISTER_USER_VIEW %>">Create a new one</a>
				</div>
			</div>
			
			<div class="description">
				<h2>Welcome to COOL_NAME</h2>
				<p>
					Some text Some text Some text Some text Some text Some text
					Some text Some textSome text Some text Some text Some text 
					Some text 
				</p>	
			</div>	
		</div>
		
		<!-- INCLUDE FOOTER -->
		<jsp:include page="_footer.jsp" />	
		
		<br />
		<p> DEBUG: index.jsp </p>
		
	</body>
</html>