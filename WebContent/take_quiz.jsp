<%@page import="quizsite.models.questions.RadioQuestion"%>
<%@page import="quizsite.models.questions.FillBlankQuestion"%>
<%@page import="quizsite.models.questions.CheckboxQuestion"%>
<%@page import="quizsite.models.questions.PictureQuestion"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="quizsite.util.*,quizsite.controllers.*,quizsite.models.*,java.util.*"%>
<%
	int quizid = Integer.parseInt(request.getParameter("quizid"));
	Quiz quiz = Quiz.get(quizid);
	List<Question> questions = quiz.getQuestions();
	Boolean isMultiple = (Boolean) request.getAttribute("is_multiple");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	
	<!-- ADD GENERAL HEAD FILE -->
	<jsp:include page="_general_head_info.jsp" />
	
	<link rel="stylesheet" href="css/take_quiz.css" type="text/css">
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
	
	<% if (isMultiple) { %>
	<script type="text/javascript" src="javascript/take_quiz_multi.js"></script>
	<% } %>
	<script type="text/javascript" src="javascript/remote_eval_quiz.js"></script>
	<title><%=quiz.getTitle()%></title>

</head>
<body>
	<!--  INCLUDE HEADER -->
	<jsp:include page="_header.jsp" />

	<!-- MAIN CONTENT START -->
	<div class="main">




		<div class="container">
			<form action="EvalQuizController" method="post" id="question_form">
				<%
				for (int i = 0; i < questions.size(); i++) {
					Question crrQuest = questions.get(i);
					String type = crrQuest.getType();
			%>

				<div id="question_num_<%=i%>" class="question">
					<%
					if (Question.Type.CHECKBOX.equals(type)) {
							CheckboxQuestion q = (CheckboxQuestion) crrQuest;
							List<String> options = q.getOptions();

							out.print(crrQuest.getText() + ". <br/>");

							for (int j = 0; j < options.size(); j++) {
								out.print("<label for='" + q.getId() + j + "'>"
										+ options.get(j) + "</label>");
								out.print("<input type='checkbox' name='answer"
										+ q.getId() + " value='" + options.get(j)
										+ "' id='" + q.getId() + j + "'/>");
								if (j % 2 == 0)
									out.print("<br/>");
							}

						} else if (Question.Type.FILL_BLANK.equals(type)) {
							out.print(crrQuest.getText() + ". <br/>");
							FillBlankQuestion q = (FillBlankQuestion) crrQuest;
							out.print(q.getFirstPart()
									+ "<input type='text' name='answer" + q.getId()
									+ "'/>" + q.getSecondPart() + "<br/>");
						} else if (Question.Type.PICTURE.equals(type)) {
							out.print(crrQuest.getText() + "? <br/>");
							out.print("<image src='"
									+ ((PictureQuestion) crrQuest).getPicUrl()
									+ "'/><br/>");
							out.print("<input type='text' name='answer"
									+ crrQuest.getId() + "'/> <br/>");
						} else if (Question.Type.RADIO.equals(type)) {
							RadioQuestion q = (RadioQuestion) crrQuest;
							List<String> options = q.getOptions();

							out.print(crrQuest.getText() + ". <br/>");

							for (int j = 0; j < options.size(); j++) {
								out.print("<label for='" + q.getId() + j + "'>"
										+ options.get(j) + "</label>");
								out.print("<input type='radio' name='answer"
										+ q.getId() + " value='" + options.get(j)
										+ "' id='" + q.getId() + j + "'/>");
								if (j % 2 == 0)
									out.print("<br/>");
							}
							out.print("radio! \n");
						} else if (Question.Type.RESPONSE.equals(type)) {
							out.print(crrQuest.getText() + "?");
							out.print("<input type='text' name='answer"
									+ crrQuest.getId() + "'/> <br/>");
						}

						out.print("<br/>");
						out.print("<br/>");
						out.print("<br/>");
						%>
					<% if (i < questions.size() - 1 ) {
					if ( isMultiple) {
				%>
					<button class="button_next" data-next-question="<%= (i+1) %>">Next</button>
					<% }} else { %>
					<input id="send_button" type="submit" value="Check me!" /> <input
						type="hidden" name="quizid" value="<%=quiz.getId()%>" />
					<% } %>
				</div>
				<%	} %>
                <input type="hidden" name="startTime" value="<%= System.currentTimeMillis() %>"/>
			</form>

		</div>
		<div id="send_result" class="container"></div>

	</div>

	<!-- INCLUDE FOOTER -->
	<jsp:include page="_footer.jsp" />
</body>
</html>
