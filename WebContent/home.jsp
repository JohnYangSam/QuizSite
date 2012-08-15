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

			</div>
			<div id="messages">
				<h3><a href="inbox">Inbox</a></h3>
				<p>Recent Messages</p>
				<div id="messageBox">
				<% Util.printUserSessionId(request); %>	
				
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

			</div>
			<div id="recentlyCreatedQuizes">
				<h3>Top Five Recently Created Quizzes</h3>

			</div>
		</div>
				

			<br />
			<p> DEBUG: home_view.jsp </p>
		</div>

		<!-- INCLUDE FOOTER -->
		<jsp:include page="_footer.jsp" />	
	</body>
</html>
