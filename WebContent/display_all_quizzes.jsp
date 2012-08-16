<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*,quizsite.controllers.*, quizsite.models.*, java.util.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/style.css" rel="stylesheet" type="text/css" />
	
		<!-- ADD GENERAL HEAD FILE -->
		<jsp:include page="_general_head_info.jsp" />
		
		<title>Quiz Book Display All Quizzes</title>
	</head>
	
	<body>
		<!--  INCLUDE HEADER -->
		<jsp:include page="_header.jsp"/>
		
		<!-- MAIN CONTENT START -->	
		<div class="main">
		<h2>Quizzes available to take</h2>	
		<%
			List<Quiz> quizzes = Quiz.index();
			for(int i = 0; i < quizzes.size(); ++i)	 {
				Quiz quiz = quizzes.get(i);
				out.println("<li>");
				out.println("<div class='data'>");
				out.println("<p>");
				out.print("<span class='number'>"+(i+1)+") </span>");
				out.print(quiz.getQuizTitleLink());	
				out.print(" created on " + quiz.getCreatedAt());
				out.println("</p>");
				out.println("</div>");
				out.println("</li>");
			}
		%>		
		
	
		</div>

		<!-- INCLUDE FOOTER -->
		<jsp:include page="_footer.jsp" />	
	
	</body>
</html>