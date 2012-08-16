<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*,quizsite.controllers.*, quizsite.models.*, java.util.*, java.lang.Integer.*" %>

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
		
		<%
		//NOTE: The user id (or profile id and user in this case is NOT the currently logged in)
		//user but instead related to the profile that the user is viewing.
			String userIdStr = request.getParameter("userId");
			int userId = -1;	
			try {		
			userId = Integer.parseInt(userIdStr);
			} catch (Exception e) { /* Ignore */ }
		
			if(userIdStr == "" || userId == 0) {
				out.println("<h2>User not found</h2>");
				out.println("<p>Sorry, that user could not be found.</p>");
			} else {
				User user = User.get(userId);
				if(user == null) {
					out.println("<h2>User not found</h2>");
					out.println("<p>Sorry, that user could not be found.</p>");
				} else	 {
					out.println("<h2>"+user.getName()+"</h2>");
					out.println("<ul>");
					out.println(" QuizBook member since " + user.getCreatedAt() + ".");
					out.println("<li>Contact: "+user.getEmail()+" </li>");	
						
					out.println("<li>");
					out.println("Achievements: ");
					
					out.println("<ul>");
					for(Achievement achievement : user.getAchievements()) {
						out.println("<li>");
						out.println("<p>"+achievement.getType().getTitle()+" achieved on "+achievement.getCreatedAt()+"</p>");
						out.println("</li>");	
					}
					out.println("</ul></li>");
					
					out.println("<li>Recent Activity");
					out.println("<ul>");
					for(Activity activity : user.getUserActivities()) {
						out.println("<li>");
						out.println(activity.getActivityPrintString());
						out.println("</li>");	
					}
					out.println("</ul></li>");
						
					out.println("</ul>");
				}
			}
		
		%>
	
		</div>

		<!-- INCLUDE FOOTER -->
		<jsp:include page="_footer.jsp" />	
	
	</body>
</html>