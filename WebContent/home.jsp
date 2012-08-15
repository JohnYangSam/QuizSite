<%@ page import="quizsite.util.*, quizsite.controllers.*, quizsite.models.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.controllers.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%
			/* User current = (User) request.getAttribute(Util.CURRENT_USER_KEY); */
		//3
			List<Attempt> attemptsByTime = Attempt.ofUserByDate(user);
			request.setAttribute("attemptsByTime", attemptsByTime);
			//4
			List<Quiz> quizListByUser = Quiz.indexCreatedBy(user);
			request.setAttribute("quizListByUser", quizListByUser);
			//5
			List<Achievement> achievements = Achievement.ofUser(user);
			request.setAttribute("achievements", achievements);
			//6
			List<Message> messages = Message.indexToUserByDate(user);
			request.setAttribute("messages", messages);
			//7
			List<Activity> friendActivities = user.getFriendActivitiesByDate();
			reque
		
		
		%>
		<jsp:include page="_general_head_info.jsp" />
		<title>Welcome!</title>
	</head>
	<body>
		<!--  INCLUDE HEADER -->
		<jsp:include page="_header.jsp"/>
	
		<div id="main">

		<!-- LEFT PANEL -->
		<div id="leftPanel">
			<div id="achievements">
				<h3>Recent Achievements</h3>
				<%
					session.getAttribute(Util.USER_SESSION_KEY);
				%>
			</div>
			<div id="messages">
				<h3><a href="inbox">Inbox</a></h3>
				<p>Recent Messages</p>
				<div id="messageBox">
				
				</div>
			</div>

			<div id="recentQuizTakeActivity">
				<h3>Recent Quizzes You Took</h3>

			</div>
			<div id="recentQuizCreateActivity">
				<h3>Recent Quizzes You Created</h3>

			</div>	
		</div>	

		<!-- CENTER PANEL -->
		<div id="centerPanel">
			<div id="announcements">
				<h2>Announcements</h2>

			</div>

			<div id="recentFriendActivity">
				<h3>Recent Friend Activity</h3>

			</div>
		</div>

		<!-- RIGHT PANEL -->
		<div id="rightPanel">
			<div id="popularQuizes">
				<h3>Top Five Most Popular Quizzes</h3>
				<%
					List<Quiz> quizListByPopularity = new request.getAttribute("quizListByPopularity");
					for(int i = 0; i < 5; ++i) {
						Quiz quiz = quizListByPopularity[i];
						out.println("<div class='data'>");
						out.println("<p>");
						out.print("<span class='number'>"+i+") </span>");
						out.print("<a href="/displaquiz.getTitle());
						out.print(" " + quiz.getAttempts());
						out.println("</p>");
						out.println("</div>");
					}
				%>	
			</div>
			<div id="recentlyCreatedQuizes">
				<h3>Top Five Recently Created Quizzes</h3>
				<% 
					//2
					List<Quiz> quizListByCreationTime = request.getAttribute("quizListByCreationTime");
					for(int i = 0; i < 5; ++i) {
						Quiz quiz = quizListByCreationTime[i];
						out.println("<div class='data'>");
						out.println("<p>");
						out.print("<span class='number'>"+i+") </span>");
						out.print(quiz.getTitle());
						out.println("</p>");
						out.println("</div>");
					}
				%>	

			</div>
		</div>
				

			<br />
			<p> DEBUG: home_view.jsp </p>
		</div>

		<!-- INCLUDE FOOTER -->
		<jsp:include page="_footer.jsp" />
			
		<% Util.printUserSessionId(request); %>	
	</body>
</html>
