<div id="header">
	<!-- HEADER INFORMATION GOES HERE -->

	<div id="loggedInHeader">
		`<ul>
			<li>User:<a href="#"><%=User.getUserName(request)%></a></li>	
			<li><a href="/home">Home</a></li>	
			<li><a href="/inbox">Inbox</a></li>	
			<li><a href="/logout_user.jsp">Logout</a></li>	
			<li><a href="#">Create a Quiz</a></li>	
			<li><a href="#">Take a Quiz</a></li>	
			<li><a href="#">View Achievements</a></li>	
		</ul>
	</div>
	
	<div id="loggedOutHeader">
		<ul>
			<li>Guest</li>
			<li><a href="/home">Home</a></li>	
			<li><a href="/login_user.jsp">Login</a></li>	
			<li><a href="/register_user.jsp"></a></li>	
		</ul>
	</div>
</div>

