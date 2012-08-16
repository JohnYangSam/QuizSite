<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<!-- ADD GENERAL HEAD FILE -->
		<jsp:include page="_general_head_info.jsp" />	
		
		<title>Quiz Book - Create a Quiz!</title>
		
		<style type="text/css">
		    div.question 
		    {
		        border:5px solid grey;
		        margin: 25px;
		        background-color: #FEF0DB;
		    }
		    
		    input 
		    {
		    	margin: 10px;	
		    }
		</style>

		</head>
	<body>
		<!--  INCLUDE HEADER -->
		<jsp:include page="_header.jsp"/>
		
		<!-- MAIN CONTENT START -->		
	<div id="main">		
		
		<!-- data-___ is the only kind of customized attributes allowed in HTML5 -->
		<div id="stash" style="display: none">
		    <div id="Fill_Blank">
		        <div class="question">
		            <label>Fill In the Blank Question | </label>
		            Question Description <input type="text" data-name="text" />
					Text to the left <input type="text" data-name="firstPart" />
		            Answers <textarea data-name="answers"> </textarea>
					Text to the right <input type="text" data-name="secondPart" />
		            <input type="hidden" data-name="type" value="fill_blank"/>
		            <p>Answers are separated by semi-colons. Please NO trailing ;'s at the end!)!</p>
		        </div>
		    </div>
		    <div id="Checkbox">
		        <div class="question">
		            <label>Checkbox Question | </label>
		            Question Description <input type="text" data-name="text" />
		            Options<textarea data-name="options"> </textarea>
		            Answers <textarea data-name="answers"> </textarea>
		            <p>Please separate options and answers by semi-colons " ; " please NO trailing ;'s at the end!</p>
		            <input type="hidden" data-name="type" value="checkbox"/>
		        </div>
		    </div>
		    <div id="Picture">
		        <div class="question">
		            <label>Picture Question | </label>
		            Question text<input type="text" data-name="text" />
		            Image url<input type="text" data-name="picUrl" />
		            Answers <textarea data-name="answers"> </textarea>
		            <input type="hidden" data-name="type" value="picture"/>
		        </div>
		    </div>
		    <div id="Radio">
		        <div class="question">
		            <label>Radio Button Question | </label>
		            Question text <input type="text" data-name="text" />
		            Options <textarea data-name="options"> </textarea>
		            Answers <textarea data-name="answers"> </textarea>
		            <p>Answers are separated by semi-colons. Please NO trailing ;'s at the end!)!</p>
		            <input type="hidden" data-name="type" value="radio"/>
		        </div>
		    </div>
		    <div id="Response">
		        <div class="question">
		            <input type="hidden" data-name="type" value="response"/>
		            <label>Response Question | </label>
		            Question Query and Description <input type="text" data-name="text" />
		            Answers <textarea data-name="answers"> </textarea>
		            <p>Answers are separated by semi-colons. Please NO trailing ;'s at the end!)!</p>
		        </div>
		    </div>
		</div>
		
		
		<h2>Choose the type of question to add to the quiz</h2>
		<p>Please be careful and patient putting in input. text areas that can take multiple answers need
		to be separated by semi-colons ( ; ) and trailing semi colons are not valid!</p>
		<div id="choice" class="options">
		    <select id="selection">
		      <option>Fill_Blank</option>
		      <option>Checkbox</option>
		      <option>Picture</option>
		      <option>Radio</option>
		      <option>Response</option>
		    </select>
		    <button type="button" id="add_question"> Add the question type to the Quiz !</button>
		</div>
		
		<div id="quiz">
			<form method="post" action="createQuiz" id="newQuizForm" class="options">
				<div class="options">
					<label for="practice">Enable practice</label><input type="checkbox" name="practiceEnabled" value="practiceEnabled" id="practice"/> <br/>
					<label for="immediate">Immediate check</label><input type="checkbox" name="immediateCheck" value="immediateCheck" id="immediate"> <br/>
					<label for="random">Random order</label><input type="checkbox" name="isRandom" value="randomOrder" id="random"/> <br />
					<label for="onePage">One page</label><input type="radio" name="onePage" value="onePageEnabled" id="onePage"/>
					<label for="flash">Flash style</label><input type="radio" name="onePage" value="flashStyle" id="flash"/><br/>
					<label for="quiz_title">Quiz title</label><input type="text" name="quiz_title" id="quiz_title"/>
					<label for="quiz_descr">Quiz description</label><input type="text" name="quiz_descr" id="quiz_descr"/>
					<label for="quiz_title">Quiz category</label><input type="text" name="quiz_category" id="quiz_category"/>
				</div>
				
				<input type="hidden" name="numOfQuest" value="0" id="numOfQuest"/>
				<input type="submit" value="Click here to finish Creating the quiz" />
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
	</div>
	</body>
</html>