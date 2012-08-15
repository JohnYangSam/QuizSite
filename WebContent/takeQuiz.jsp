<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizsite.util.*, quizsite.controllers.*, quizsite.models.*, java.util.*" %>
<%
	//User current = Util.signInOrRedirect(request, response);
	
	//System.out.println("Outside redirect in home controller hit: " + current);
	//if(current == null) return;
	
	int quizid = 1; 
	Quiz quiz  = Quiz.get(quizid);
	
	List<Question> questions = quiz.getQuestions();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz</title>
</head>
<body>
<%
	for(int i=0; i < questions.size(); i++)
	{
		System.out.print(questions.get(i).getType());
	}
%>
<div data-name="" class="question">
    <div id="Checkbox">
        <div class="question">
            <label>This is a fill blank</label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Fill_Blank">
        <div class="question">
            <label>This is a fill blank</label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Picture">
        <div class="question">
            <label>This is a picture</label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Radio">
        <div class="question">
            <label> This is a radio </label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Response">
        <div class="question">
            <label> This is a response </label>
            <textarea data-name="text"> </textarea>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
            <select data-name="selector"></select>
        </div>
    </div>
</div>
</body>
</html>