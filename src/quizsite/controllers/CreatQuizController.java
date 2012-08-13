package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizsite.models.Question;
import quizsite.models.Quiz;
import quizsite.models.User;
import quizsite.models.questions.CheckboxQuestion;
import quizsite.models.questions.FillBlankQuestion;
import quizsite.models.questions.PictureQuestion;
import quizsite.models.questions.RadioQuestion;
import quizsite.models.questions.ResponseQuestion;

/**
 * Servlet implementation class CreatQuizController
 */
@WebServlet({"/CreatQuizController", "/createQuiz"})
public class CreatQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO all blank strings and fields should be correctly set after I write an actual view 
		try {
			User currentUser = new User(1);//Util.signInOrRedirect(request, response);
			
			boolean onePage 		= false;
			boolean practice 		= false;
			boolean immediateCheck  = false;
			boolean random 			= false;
			int creatorID 			= currentUser.getId();
		
			Quiz newQuiz = new Quiz(onePage, practice, immediateCheck, random, creatorID);
			int newQuizId	 = newQuiz.save();
			
			int numOfQuestions = 1;
			for (int i = 0; i < numOfQuestions; i++) {
				
				String type    = "checkbox"; 
				String text    = ""; 
				// all answers should be separeted by a ;
				Set<String> answers = new HashSet<String>(Arrays.asList("".split(";")));
				
				Question q = null;
				if (Question.Type.CHECKBOX.equals(type))
				{
					q = new CheckboxQuestion(answers, text, newQuizId);
				} else if (Question.Type.FILL_BLANK.equals(type))
				{
					String first  = "";
					String second = ""; 
					q = new FillBlankQuestion(answers, first, second, newQuizId);
				} else if (Question.Type.PICTURE.equals(type))
				{
					String picURL = "";
					q = new PictureQuestion(answers, text, newQuizId, picURL);
				} else if (Question.Type.RADIO.equals(type))
				{
					q = new RadioQuestion(answers, text, newQuizId);
				} else if (Question.Type.RESPONSE.equals(type))
				{
					q = new ResponseQuestion(answers, text, newQuizId);
				} else throw new Exception("Invalid type of a question!");			
				
				q.save();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO redirect user somewhere after succesull adding of a question
	}

}
