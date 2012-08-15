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
            Question text<input type="text" data-name="text" />
            Options <textarea data-name="options"> </textarea>
            Answers <textarea data-name="answers"> </textarea>
            <input type="hidden" data-name="type" value="checkbox"/>
        </div>
    </div>
    <div id="Fill_Blank">
        <div class="question">
            <label>Fill</label>
            Text <input type="text" data-name="text" />
			First part <input type="text" data-name="firstPart" />
			Second part <input type="text" data-name="secondPart" />
            Answers <textarea data-name="answers"> </textarea>
            <input type="hidden" data-name="type" value="fill_blank"/>
        </div>
    </div>
    <div id="Picture">
        <div class="question">
            <label>Picture</label>
            Question text<input type="text" data-name="text" />
            Image url<input type="text" data-name="picUrl" />
            Answers <textarea data-name="answers"> </textarea>
            <input type="hidden" data-name="type" value="picture"/>
        </div>
    </div>
    <div id="Radio">
        <div class="question">
            <label>Radio</label>
            Questio text <input type="text" data-name="text" />
            Options <textarea data-name="options"> </textarea>
            Answers <textarea data-name="answers"> </textarea>
            <input type="hidden" data-name="type" value="radio"/>
        </div>
    </div>
    <div id="Response">
        <div class="question">
            <input type="hidden" data-name="type" value="response"/>
            <label>Response</label>
            Questio text <input type="text" data-name="text" />
            Answers <textarea data-name="answers"> </textarea>
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
		<div class="options">
			<label for="practice">Enable practice</label><input type="checkbox" name="practiceEnabled" value="practiceEnabled" id="practice"/> <br/>
			<label for="immediate">Immediate check</label><input type="checkbox" name="immediateCheck" value="immediateCheck" id="immediate"> <br/>
			<label for="random">Random order</label><input type="checkbox" name="isRandom" value="randomOrder" id="random"/> <br />
			<label for="onePage">One page</label><input type="radio" name="onePage" value="onePageEnabled" id="onePage"/>
			<label for="flash">Flash style</label><input type="radio" name="onePage" value="flashStyle" id="flash"/> 
		</div>
		
		<input type="hidden" name="numOfQuest" value="0" id="numOfQuest"/>
		<input type="submit" value="Create">
    <div id="count">0</div>
	</form>
</div>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#add_question").click(function() {
            var count = parseInt($("#numOfQuest").val());
            $("#newQuizForm").append(($("#" + $("#selection").val()).html()));
            
            // Update attributes
            $.each(
                
                $("#newQuizForm > div.question").last().children(),    // update this if you change the class name from 'question' to something else
                function() {
                    $(this).attr('name', $(this).attr('data-name') + ":" + count);
                }
            );
            
            // Update count
            $("#numOfQuest").val(parseInt($("#numOfQuest").val()) + 1);
        });
    });
    
</script>

<br />
<p> DEBUG: create_quiz.jsp </p>
</body>
</html>