<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*,quizsite.controllers.*, quizsite.models.*, java.util.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/homePage.css" rel="stylesheet" type="text/css" />	
	
		<!-- ADD GENERAL HEAD FILE -->
		<jsp:include page="_general_head_info.jsp" />
		
		<title>Quiz Book</title>
	</head>
	
	<body>
		<!--  INCLUDE HEADER -->
		<jsp:include page="_header.jsp"/>
		
		<!-- MAIN CONTENT START -->	
		<div class="main">
		<h1>Users on Quizbook!</h1>
		<ul>
		<%
			List<User> users = User.index();
			for(User user : users) {
				out.println("<li>");
				out.print("<a href='display_user.jsp?userId="+user.getId()+"'>" + user.getName()+"</a> ");
				out.print(" QuizBook member since " + user.getCreatedAt() + ".");
				//out. send_friend_request? initiator_Id responder_Id 	
				out.println("</li>");	
			}
		%>
		</ul>
	
		</div>

		<!-- INCLUDE FOOTER -->
		<jsp:include page="_footer.jsp" />	
	
	</body>
</html>