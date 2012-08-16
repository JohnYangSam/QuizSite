package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizsite.models.Quiz;
import quizsite.models.User;

/**
 * Servlet implementation class DisplayQuizController
 */
@WebServlet({ "/DisplayQuizController", "/display_quiz", "/take_quiz" })
public class DisplayQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User current = Util.signInOrRedirect(request, response);
		if (current == null) return;
		
		Integer quizId = Integer.parseInt(request.getParameter("quizid"));
		Quiz quiz;
		RequestDispatcher dispatch ;
		try {
			if (quizId != null && (quiz = Quiz.get(quizId)) != null ) {
				// quiz exists, let's check it's properties
				if (quiz.isOnePage()) {
					dispatch = request.getRequestDispatcher("display_quiz_one_page.jsp");
				} else {	// multiple page
					if (!quiz.isImmediate()) {
						dispatch = request.getRequestDispatcher("display_quiz_multi_page.jsp");
					} else { // immediate, multiple page
						dispatch = request.getRequestDispatcher("display_quiz_multi_page_immed.jsp");
					}
				}
				dispatch.forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("failureMessage", "We encountered some error. Please try again later. Not sent.");
			dispatch = request.getRequestDispatcher("index.jsp");
			dispatch.forward(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
