package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
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
@WebServlet({"/CreateQuizController", "/createQuiz"})
public class CreateQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizController() {
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
		Quiz newQuiz;
		try {
			User currentUser = Util.signInOrRedirect(request, response);
			
			boolean onePage 		= (request.getParameter("onePage").equals("onePageEnabled"))?true:false;
			boolean practice 		= (request.getParameter("practiceEnabled") == null)?false:true;
			boolean immediateCheck  = (request.getParameter("immediateCheck") == null)?false:true;
			boolean random 			= (request.getParameter("isRandom") == null)?false:true;
			String title			= request.getParameter("quiz_title");
			String descr			= request.getParameter("quiz_descr");
			String category			= request.getParameter("quiz_category");
			int creatorID 			= currentUser.getId();
		
			newQuiz = new Quiz(title, descr, category, onePage, practice, immediateCheck, random, creatorID);
			int newQuizId	 = newQuiz.save();
			
			int numOfQuestions = Integer.parseInt(request.getParameter("numOfQuest"));
			for (int i = 0; i < numOfQuestions; i++) {
				
				String type    = request.getParameter("type:"+i); 
				String text    = request.getParameter("text:"+i); 
				// all answers should be separeted by a ;
				Set<String> answers = new HashSet<String>(Arrays.asList(request.getParameter("answers:"+i).trim().split(";")));
				
				Question q = null;
				if (Question.Type.CHECKBOX.equals(type))
				{
					List<String> opts = new ArrayList<String>(Arrays.asList(request.getParameter("options:"+i).trim().split(";")));
					q = new CheckboxQuestion(answers, text, newQuizId, opts);
				} else if (Question.Type.FILL_BLANK.equals(type))
				{
					String first  = request.getParameter("firstPart:"+i);
					String second = request.getParameter("secondPart:"+i); 
					q = new FillBlankQuestion(answers, first, second, newQuizId);
				} else if (Question.Type.PICTURE.equals(type))
				{
					String picURL = request.getParameter("picUrl:"+i);
					q = new PictureQuestion(answers, text, newQuizId, picURL);
				} else if (Question.Type.RADIO.equals(type))
				{
					List<String> opts = new ArrayList<String>(Arrays.asList(request.getParameter("options:"+i).trim().split(";")));
					q = new RadioQuestion(answers, text, newQuizId, opts);
				} else if (Question.Type.RESPONSE.equals(type))
				{
					q = new ResponseQuestion(answers, text, newQuizId);
				} else throw new Exception("Invalid type of a question!");			
				
				q.save();
				
			}
			
			request.setAttribute("failureMessage", "You sucessfully created a quiz!");
			RequestDispatcher dispatch = request.getRequestDispatcher("displayQuiz.jsp?quizId="+newQuiz.getId());
			dispatch.forward(request, response);
			return;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO redirect user somewhere after successfully adding of a question
		//System.out.println("Saved");
	}

}
