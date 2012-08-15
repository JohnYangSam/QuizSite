<%@ page import="quizsite.models.*,quizsite.controllers.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*,quizsite.controllers.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/inboxPage.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="javascript/inboxPage.js"></script>

<title>Inbox View</title>
</head>
<body>
	<%
		User currentUser = Util.signInOrRedirect(request, response);
		List<Message> received = Message.indexTo(currentUser);
		List<Message> sent = Message.indexFrom(currentUser);
	%>
	<div class="parent">
		<div id="inbox" class="container">
			<%="Currently signed in as " + currentUser.getName()%>
			<!-- Received messages -->
			<h2>Received messages</h2>
			<%="Has " + received.size() + " received messages"%>
			<br />
			<table>
				<thead>
					<tr>
						<th class="date">Date</th>
						<th class="from">From</th>
						<th class="subject">Subject</th>
						<th class="body">Body</th>
						<th class="respond">Respond</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Message message : received) {
					%>
					<tr>
					
						<td class="date"><%= message.getCreatedAt() %></td>
						<td class="from"><%=message.getSender().getName()%></td>
						<td class="subject"><%=message.getSubject()%></td>
						<td class="body"><%=message.getBody()%></td>
						<td class="respond">
							<button data-to="<%=message.getSender().getName()%>"
								data-subject="re:<%=message.getSubject()%>">Reply!</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>

			<br>
			<hr>
			<br>

			<h2>Sent messages</h2>
			<%="Has " + sent.size() + " sent messages"%>
			<br/>
			<table>
				<thead>
					<tr>
						<th class="date">Date</th>
						<th class="to">To</th>
						<th class="subject">Subject</th>
						<th class="body">Body</th>
						<th class="respond">Respond</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Message message : sent) {
					%>
					<tr>
						<td class="date"><%=message.getCreatedAt()%></td>
						<td class="to"><%=message.getRecipient().getName()%></td>
						<td class="subject"><%=message.getSubject()%></td>
						<td class="body"><%=message.getBody()%></td>
						<td class="respond">
							<button data-to="<%=message.getRecipient().getName()%>"
								data-subject="reminder: <%=message.getSubject()%>">
								Follow up!</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>

		<div></div>

		<div id="compose" class="container">
		<h2>Compose</h2>
		<div id="send_result">
			<br />
		</div>
			<form action="send_message" method="post">
				<ul>
					<li><label>To: </label></li>
					<li> 
						<textarea name="to" id="compose_to" cols=100></textarea>
					</li>
					<li><label>Subject: </label></li>
					<li>  
						<textarea name="subject" id="compose_subject" cols=100></textarea>
					</li>
					<li> <label>Body: </label> </li> 
					<li>
						<textarea name="body" id="compose_body" rows=10 cols=100></textarea>
					</li>
					<li>
						<input id="send_button" type="submit" value="Send!" />
					</li>
				</ul>
			</form>
			<p>DEBUG: inbox.jsp</p>
			<%
		Util.printUserSessionId(request);
	%>
		</div>

	</div>



</body>
</html>