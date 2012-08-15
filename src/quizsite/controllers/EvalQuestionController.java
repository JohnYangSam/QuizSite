package quizsite.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizsite.models.Question;

/**
 * Servlet implementation class EvalQuestionController
 */
@WebServlet("/EvalQuestionController")
public class EvalQuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvalQuestionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * answer field should look like this:
	 * <input type="text" name="answer3" /> where 3 is a question id, the same id as in db
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qid    = Integer.parseInt(request.getParameter("questid"));
		try {
			Question q = Question.get(qid);
			PrintWriter out = response.getWriter();
			out.println(q.getScore((new HashSet<String>(Arrays.asList(request.getParameter("answer"+qid))))));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
