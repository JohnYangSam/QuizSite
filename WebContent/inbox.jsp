<%@ page import="quizsite.models.*,quizsite.controllers.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*,quizsite.controllers.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/inboxPage.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="javascript/inboxPage.js"></script>

<title>Inbox View</title>
</head>
<body>
	<%
		User currentUser = Util.signInOrRedirect(request, response);
		List<Message> received = Message.indexTo(currentUser);
		List<Message> sent = Message.indexFrom(currentUser);
	%>

	<div class="inbox">
		<br> <br>
		<%="Currently signed in as " + currentUser.getName()%>
		<!-- Received messages -->
		<br>
		<%="Has " + received.size() + " received messages"%>
		<table>
			<thead>
				<tr>
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
					<td class="from"><%=message.getSender().getName()%></td>
					<td class="subject"><%=message.getSubject()%></td>
					<td class="body"><%=message.getBody()%></td>
					<td class="respond"><button>Reply!</button> </td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

		<br>
		<hr>
		<br>

		<!-- Sent messages -->
		<%="Has " + sent.size() + " sent messages"%>
		<table>
			<thead>
				<tr>
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
					<td class="to"><%=message.getRecipient().getName()%></td>
					<td class="subject"><%=message.getSubject()%></td>
					<td class="body"><%=message.getBody()%></td>
					<td class="respond"><button>Follow up!</button>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<br />
	<p>DEBUG: inbox.jsp</p>
	<%
		Util.printUserSessionId(request);
	%>

</body>
</html>