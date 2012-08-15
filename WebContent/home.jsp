<%@ page import="quizsite.util.*, quizsite.controllers.*, quizsite.models.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.models.*, quizsite.controllers.*, java.util.*, java.lang.Exception.*, java.sql.*" %>

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
				<h3>Your Achievements</h3>
				<ul>
				<%
					//5
					List<Achievement> achievementsByTime = (List<Achievement>)request.getAttribute("achievements");
					if(achievementsByTime == null) {
						out.println("<p>You do not have any achievements yet.</p>");
					} else {
						for(int i = 0; i < achievementsByTime.size(); ++i) {
							Achievement achievement = achievementsByTime.get(i);
							out.println("<li>");
							out.println("<p>"+achievement.getType().getTitle()+"</p>");
							out.println("</li>");	
						}
					}
				%>
				</ul>
			</div>
			<div id="messages">
				<h3><a href="inbox">Inbox</a></h3>
				<h2>Recent Messages</h2>
				<div id="messageBox">
				<ul>	
				<%
					List<Message> messages = (List<Message>)request.getAttribute("messages");
					if(messages == null) {
						out.println("<p>No recent messages.</p>");
					} else {
						for(int i = 0; i < 5 && i < messages.size(); ++i) {
							Message message = messages.get(i);
						}
					}
				%>
				</ul>	
				</div>
			</div>

			<div id="recentQuizTakeActivity">
				<h3>Recent Quizzes You Took</h3>
				<ul>	
				<%
				//3
					List<Attempt> attemptsByTime = (List<Attempt>)request.getAttribute("attemptsByTime");
					if(attemptsByTime == null) {
						out.println("<p>No recently taken quizzes.</p>");
					} else {
						for(int i = 0; i < 5 && i < attemptsByTime.size(); ++i) {
							Attempt attempt = attemptsByTime.get(i);
							Quiz quiz = attempt.getQuiz();
							out.println("<li>");
							out.println("<div class='data'>");
							out.println("<p>");
							out.print("You attempted "+quiz.getQuizTitleLink()+" on "+attempt.getCreatedAt()+" and recieved a score of "+attempt.getScore());	
							out.println("</p>");
							out.println("</div>");	
							out.println("</li>");
						}	
					}
				%>
				</ul>
			</div>
			<div id="recentQuizCreateActivity">
				<h3>Recent Quizzes You Recently Created</h3>
				<ul>
				<%
					List<Quiz> quizListByUser = null; //(List<Quiz>)request.getAttribute("quizListByUser");
					if(quizListByUser == null || quizListByUser.size() == 0) {
						out.println("<p>You did not recently create any quizzes.</p>");
					} else {
						for(int i = 0; i < 5 && i < quizListByUser.size(); ++i) {
							Quiz quiz = quizListByUser.get(i);	
							out.println("<li>");
							out.println("<div class='data'>");
							out.println("<p>");
							out.print("You created "+quiz.getTitle()+" on "+quiz.getCreatedAt()+ ". So far it has been attempted "+quiz.getAttempts().size()+" times.");	
							out.println("</p>");
							out.println("</div>");	
							out.println("</li>");
						}
					}	
				%>
				</ul>
			</div>	
		</div>	

		<!-- CENTER PANEL -->
		<div id="centerPanel">
			<div id="announcements">
				<h2>Announcements</h2>
				<ul>	
				<%
				//NEED TO CREATE THE ABILITY TO GET ANNOUNCEMENTS!
				List<Announcement> announcements = (List<Announcement>)request.getAttribute("announcements");
				if(announcements == null) {
					out.println("<p>There are no recent announcements.</p>");
				} else {
				
					//Print out the Announcements
					
				}
				%>
				</ul>
			</div>

			<div id="recentFriendActivity">
				<h3>Recent Friend Activity</h3>
				<ul>
				<%
				//7
				List<Activity> friendActivities = (List<Activity>)request.getAttribute("friendActivities");
				if(friendActivities == null) {
					out.println("<p>There is no recent friend activity.</p>");
				} else {
					for(Activity activity : friendActivities) {
						out.println("<li>");
						out.println(activity.getActivityPrintString());
						out.println("</li>");
					}
				}
				%>
				</ul>
			</div>
		</div>

		<!-- RIGHT PANEL -->
		<div id="rightPanel">
			<div id="popularQuizes">
				<h3>Top Five Most Popular Quizzes</h3>
				<ul>
				<%
					List<Quiz> quizListByPopularity = (List<Quiz>)request.getAttribute("quizListByPopularity");
					if(quizListByPopularity == null) {
						out.println("<p>There are no popular quizzes.</p>");
					} else {
						for(int i = 0; i < 5 && i < quizListByPopularity.size(); ++i) {
							Quiz quiz = quizListByPopularity.get(i);
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
					}
				%>
				</ul>
			</div>
			<div id="recentlyCreatedQuizes">
				<h3>Top Five Most Recently Created Quizzes</h3>
				<ul>
				<% 
					//2
					List<Quiz> quizListByCreationTime = (List<Quiz>)request.getAttribute("quizListByCreationTime");
					if(quizListByCreationTime == null) {
						out.println("<p>There are no recently created quizzes.</p>");
					} else {
						for(int i = 0; i < 5 && i < quizListByCreationTime.size(); ++i) {
							try { 
								Quiz quiz = quizListByCreationTime.get(i);
								out.println("<li>");
								out.println("<div class='data'>");
								out.println("<p>");
								out.print("<span class='number'>"+(i+1)+") </span>");
								out.print(quiz.getQuizTitleLink());	
								out.print(" attempted " + quiz.getAttempts().size() + " times");
								out.println("</p>");
								out.println("</div>");
								out.println("</li>");
							} catch (SQLException e) {
								System.err.println("Error getting number of attempts for quiz print string");
								e.printStackTrace();
							}
						}
					}
				%>
				</ul>
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
