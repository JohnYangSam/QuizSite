package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;
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

import quizsite.models.Attempt;
import quizsite.models.Question;
import quizsite.models.Quiz;

/**
 * Servlet implementation class EvalQuizController
 */
@WebServlet({"/EvalQuizController", "/eval_quiz"})
public class EvalQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalQuizController() {
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
		int quizid 		 = Integer.parseInt(request.getParameter("quizid"));
		Quiz quiz = null;
		int score = 0;
		try {
			quiz = Quiz.get(quizid);
			List<Question> q = quiz.getQuestions();
			
			for (int i = 0; i < q.size(); i++) {
				int qid = q.get(i).getId();
				String answer = request.getParameter("answer"+qid); 
				Set<String> receivedAnswers = new HashSet<String>(Arrays.asList(answer));
				score += q.get(i).getScore(receivedAnswers);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!quiz.isPracticeEnabled())
		{
			try {
				Attempt attpt = new Attempt(quiz, Util.signInOrRedirect(request, response), score);
				attpt.save();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
<<<<<<< HEAD
		System.out.println("total score = "+score + (System.currentTimeMillis() - Long.parseLong(request.getParameter("startTime"))));
		request.setAttribute("successMessage", "total score = "+score+ " time taken = "+(System.currentTimeMillis() - Long.parseLong(request.getParameter("startTime"))));
=======
		System.out.println("total score = "+score);
		request.setAttribute("successMessage", "total score = "+score+ " time taken = "+(System.currentTimeMillis() - 
				Long.parseLong(request.getParameter("startTime"))));
>>>>>>> d72aacff3bf74572face60ac9d9ccd636a51291c
		RequestDispatcher dispatch = request.getRequestDispatcher("_notify_message.jsp");
		dispatch.forward(request, response);
	}

}
