package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizsite.models.Achievement;
import quizsite.models.Attempt;
import quizsite.models.Message;
import quizsite.models.Quiz;
import quizsite.models.User;

/**
 * Servlet implementation class homeController
 */
@WebServlet({"/HomeController", "/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Check user login
		User current = Util.signInOrRedirect(request, response);
		
		System.out.println("Outside redirect in home controller hit: " + current);
		if(current == null) return;
		
		/*
		 * We want to output:
		 * 		(optional) Announcements
		 * 		2) Popular quizzes
		 * 		3) Recently created quizzes
		 * 		4) Recent quiz taking activity (user)
		 * 		8) Recent quiz creating activity
		 *
		 * 		5) Achievements (user)
		 * 		6) Messages
		 * 		7) Recent Friend activity
		 */

		try {
			//Get lists
			
			List<Quiz> quizListByPopularity = Quiz.indexByNumberOfAttempts();
			List<Quiz> quizListByCreationTime = Quiz.indexByCreationTime();
			List<Attempt> attemptsByTime = Attempt.ofUserByDate(current);
			List<Quiz> quizListByUser = Quiz.indexCreatedBy(current);
			List<Achievement> achievements = Achievement.ofUser(current);
			List<Message> messages = Message.indexToUserByDate(current);
			
			List<Object> activities = new ArrayList<Object>();
			activities.addAll(quizListByUser);
			activities.addAll(achievements);
			activities.addAll(attemptsByTime);
			
			List<User> friends = current.getFriends();
			
			
			for(User friend : friends) {
				
			}
			
			
			
		} catch (SQLException e) {
			System.err.println("There was an error drawing information about the current user");
			e.printStackTrace();
		}
		
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp");
		dispatch.forward(request, response);
		return;
	}
	

	
		List<Quiz> quizListByPopularity = Quiz.indexByNumberOfAttempts();
			List<Quiz> quizListByCreationTime = Quiz.indexByCreationTime();
			List<Attempt> attemptsByTime = Attempt.ofUserByDate(current);
			List<Quiz> quizListByUser = Quiz.indexCreatedBy(current);
			List<Achievement> achievements = Achievement.ofUser(current);
			List<Message> messages = Message.indexToUserByDate(current);
			
			List<Object> activities = new ArrayList<Object>();
			activities.addAll(quizListByUser);
			activities.addAll(achievements);
			activities.addAll(attemptsByTime);
			
			List<User> friends = current.getFriends();
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
