<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.models.*, quizsite.controllers.*, java.util.*, java.lang.Exception.*, java.sql.*" %>
<div id="header">
	<!-- HEADER INFORMATION GOES HERE -->
	<div id="loggedInHeader" class="headerList">
		`<ul class="headerListUl">
			<li class="headerElem">User:<a href="#"> <%=User.getUserName(request)%> </a></li>	
			<li class="headerElem"><a href="home">Home</a></li>	
			<li class="headerElem"><a href="inbox">Inbox</a></li>	
			<li class="headerElem"><a href="logout_user.jsp">Logout</a></li>	
			<li class="headerElem"><a href="create_quiz.jsp">Create a Quiz</a></li>	
			<li class="headerElem"><a href="display_all_quizzes.jsp">Take a Quiz</a></li>	
			<li class="headerElem"><a href="display_all_users.jsp">Find a Friend</a></li>
			<li class="headerElem"><a href="#">Achievements</a></li>
		</ul>
	</div>

<!--	
	<div id="loggedOutHeader" class="headerList">
		<ul class="headerListUl">
			<li class="headerElem">Guest</li>
			<li class="headerElem"><a href="/home">Home</a></li>	
			<li class="headerElem"><a href="/login_user.jsp">Login</a></li>	
			<li class="headerElem"><a href="/register_user.jsp"></a></li>	
		</ul>
	</div>

	-->
</div>


