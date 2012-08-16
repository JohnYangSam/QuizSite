<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.models.*, quizsite.controllers.*, java.util.*, java.lang.Exception.*, java.sql.*" %>
<div id='header'>
	<!-- HEADER INFORMATION GOES HERE -->
	
<%  
Object userIdObj = session.getAttribute(Util.USER_SESSION_KEY);
	if(userIdObj != null) {
	out.println("<div id='loggedInHeader' class='headerList'>");
	out.println("	<ul class='headerListUl'>");
	out.println("		<li class='headerElem'>User:<a href='home'>" + User.getUserName(request) + "</a></li>");
	out.println("		<li class='headerElem'><a href='home'>Home</a></li>	");
	out.println("		<li class='headerElem'><a href='inbox'>Inbox</a></li>	");
	out.println("		<li class='headerElem'><a href='logout_user.jsp'>Logout</a></li>	");
	out.println("		<li class='headerElem'><a href='create_quiz.jsp'>Create a Quiz</a></li>");	
	out.println("		<li class='headerElem'><a href='display_all_quizzes.jsp'>Take a Quiz</a></li>	");
	out.println("		<li class='headerElem'><a href='display_all_users.jsp'>Find a Friend</a></li>");
	out.println("	</ul>");
	out.println("</div>");
	} else {
		out.println("<div id='loggedOutHeader' class='headerList'>");
		out.println("	<ul class='headerListUl'>");
		out.println("		<li class='headerElem'>Guest</li>");
		out.println("		<li class='headerElem'><a href='home'>Home</a></li>	");
		out.println("		<li class='headerElem'><a href='login_user.jsp'>Login</a></li>	");
		out.println("		<li class='headerElem'><a href='register_user.jsp'>Register</a></li>	");
		out.println("	</ul>");
		out.println("</div>");
	}
%>
</div>


