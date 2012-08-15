<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.controllers.*, quizsite.models.*" %>
<%
	//User current = Util.signInOrRedirect(request, response);
	
	//System.out.println("Outside redirect in home controller hit: " + current);
	//if(current == null) return;
	
	int quizid = Integer.parseInt(request.getParameter("quizId")); 
	Quiz quiz  = Quiz.get(quizid);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> <%= quiz.getTitle() %> </h1>
<p> <%= quiz.getDescr() %> </p>
<div class="quizSettings">
	<ul>
		<li>
			Category: <%= quiz.getCategory() %>
		</li>
		<li>
			Is random - <%= quiz.isRandomized() %>
		</li>
		<li>
			Is one page - <%= quiz.isOnePage() %>
		</li>
		<li>
			Is immediate - <%= quiz.isImmediate() %>
		</li>
		<li>
			Is practice enabled - <%= quiz.isPracticeEnabled() %>
		</li>
	</ul>
</div>
<a href="#">challenge your friend</a> <br/>
<a href="takeQuiz.jsp?quizid=<%= quizid %>">take this quiz!</a>
</body>
</html>