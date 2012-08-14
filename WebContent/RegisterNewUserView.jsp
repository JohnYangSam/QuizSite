<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/homePage.css" rel="stylesheet" type="text/css" />

<title>Quizbook - Register User</title>
</head>
<body>
	<div class="main">
		<div class="login">
			<h2>Create an account</h2>
			<form action="RegisterNewUserController" method="post">
				<ul>
					<li>
						<label>User Name</label><br/>
						<input type="text" name="userName" />	
					</li>
					<li>
						<label>Email</label>
						<input typ="text" name="email" />	
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
				<a href="index.jsp">Back</a>
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
</body>
</html>