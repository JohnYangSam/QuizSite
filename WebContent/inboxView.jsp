<%@ page import="quizsite.models.*, quizsite.controllers.*, java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inbox View</title>
</head>
<body>
<% 
	List<Message> received = (List<Message>) request.getAttribute(Util.RECEIVED_MSG_LIST_KEY);
	List<Message> sent = (List<Message>) request.getAttribute(Util.SENT_MSG_LIST_KEY);
%>

<!-- Received messages -->
<table>
	<thead>
		<tr>
			<th>From</th>
			<th>Body</th>
		</tr>
	</thead>
	<tbody>
	<% for (Message message : received) { %>
		<tr>
			<td><%= message.getSender().getName() %></td>
			<td><%= message.getBody() %></td>
		</tr>
	<% } %>
	</tbody>
</table>


<!-- Sent messages -->
<table>
	<thead>
		<tr>
			<th>To</th>
			<th>Body</th>
		</tr>
	</thead>
	<tbody>
	<% for (Message message : sent) { %>
		<tr>
			<td><%= message.getRecipient().getName() %></td>
			<td><%= message.getBody() %></td>
		</tr>
	<% } %>
	</tbody>
</table>

</body>
</html>