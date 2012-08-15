<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<style type="text/css">
    div.question {
        border:2px solid red;
    }
</style>

<!-- data-___ is the only kind of customized attributes allowed in HTML5 -->
<div id="stash" style="display: none">
    <div id="Checkbox">
        <div class="question">
            <label>Checkbox</label>
            <textarea data-name="text" name="text"></textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Fill_Blank">
        <div class="question">
            <label>Fill</label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Picture">
        <div class="question">
            <label>Picture</label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Radio">
        <div class="question">
            <label>Radio</label>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
        </div>
    </div>
    <div id="Response">
        <div class="question">
            <label>Response</label>
            <textarea data-name="text"> </textarea>
            <textarea data-name="text"> </textarea>
            <select data-name="selector"></select>
            <select data-name="selector"></select>
        </div>
    </div>
</div>


<div id="choice">
    <select id="selection">
      <option>Checkbox</option>
      <option>Fill_Blank</option>
      <option>Picture</option>
      <option>Radio</option>
      <option>Response</option>
    </select>
    <button type="button" id="add_question"> Add !</button>
</div>

<div id="quiz">
	<form method="post" action="createQuiz" id="newQuizForm">
		<input type="hidden" name="numOfQuest" value="5"/>
		<input type="submit" value="Create">
	</form>
    <div id="count">0</div>
</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#add_question").click(function() {
            var count = parseInt($("#count").html());
            $("#quiz").append(($("#" + $("#selection").val()).html()));
            
            // Update attributes
            $.each(
                
                $("#quiz > div.question").last().children(),    // update this if you change the class name from 'question' to something else
                function() {
                    $(this).attr('name', $(this).attr('data-name') + ":" + count);
                }
            );
            
            // Update count
            $("#count").html( count + 1);
        });
    });
    
</script>

<br />
<p> DEBUG: create_quiz.jsp </p>
</body>
</html>