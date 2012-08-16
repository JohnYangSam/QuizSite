<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.controllers.*, quizsite.models.*, java.util.*" %>
<%
	User current = Util.signInOrRedirect(request, response);
	
	System.out.println("Outside redirect in home controller hit: " + current);
	if(current == null) return;
	
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
		<li>
			<a href="display_user.jsp?userId=<%= quiz.getCreatorID() %>">creator</a>
		</li>
	</ul>
</div>
<div>
	<ul>
		<li>
		<h3>Your attempts</h3>
		<%
			List<Attempt> attempts = Attempt.ofUserAtQuiz(current, quiz);
			
			for(int i = 0; i < attempts.size(); ++i) {
				Attempt attempt = attempts.get(i);
				out.println("<li>");
				out.println("<div class='data'>");
				out.println("<p>");
				out.print("You attempted on "+attempt.getCreatedAt()+" and recieved a score of "+attempt.getScore());	
				out.println("</p>");
				out.println("</div>");	
				out.println("</li>");
			}	
			
		%>
		</li>
		<li>
			<h3>Recent attempts</h3>
			<%
				List<Attempt> allAttps = Attempt.atQuiz(quiz);
				for(int i = 0; i < allAttps.size(); ++i) {
					Attempt attempt = allAttps.get(i);
					User attempter = attempt.getAttempter();
					out.println("<li>");
					out.println("<div class='data'>");
					out.println("<p>");
					out.print("<a href='display_user.jsp?userId='"+attempter.getId()+"'>"+attempter.getName()+"</a> "+attempt.getCreatedAt()+" and recieved a score of "+attempt.getScore());	
					out.println("</p>");
					out.println("</div>");	
					out.println("</li>");
				}	
			%>

		</li>
		<li>
			<h3>TOP 5</h3>
			<%
				List<Attempt> topAttempts = Attempt.atQuiz(quiz);
				Collections.sort(topAttempts, 
					new Comparator<Attempt>() {
						@Override
						public int compare(Attempt o1, Attempt o2) {
							return o2.getScore() - o1.getScore();
						}
					});
				
				for(int i = 0; i < 5 && i < topAttempts.size(); ++i) {
					Attempt attempt = topAttempts.get(i);
					User attempter = topAttempts.get(i).getAttempter();
					out.println("<li>");
					out.println("<div class='data'>");
					out.println("<p>");
					out.print("<a href='display_user.jsp?userId='"+attempter.getId()+"'>"+attempter.getName()+"</a> "+topAttempts.get(i).getCreatedAt()+" and recieved a score of "+topAttempts.get(i).getScore());	
					out.println("</p>");
					out.println("</div>");	
					out.println("</li>");
				}	
			%>
		</li>
	</ul>
</div>
<a href="#">challenge your friend</a> <br/>
<a href="takeQuiz.jsp?quizid=<%= quizid %>">take this quiz!</a>
</body>
</html>