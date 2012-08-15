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
import quizsite.util.Activity;
import quizsite.util.PersistentModel;

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
		User user = Util.signInOrRedirect(request, response);
		
		System.out.println("Outside redirect in home controller hit: " + user);
		if(user == null) return;
		
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
			//Get and set the two main activity lists
		
			//1
			List<Quiz> quizListByPopularity = Quiz.indexByNumberOfAttempts();
			request.setAttribute("quizListByPopularity", quizListByPopularity);
			//2
			List<Quiz> quizListByCreationTime = Quiz.indexByCreationTime();
			request.setAttribute("quizListByCreationTime", quizListByCreationTime);
			//3
			List<Attempt> attemptsByTime = Attempt.ofUserByDate(user);
			request.setAttribute("attemptsByTime", attemptsByTime);
			//4
			List<Quiz> quizListByUser = Quiz.indexCreatedByUserAndByDate(user);
			request.setAttribute("quizListByUser", quizListByUser);
			//5
			List<Achievement> achievements = user.getAchievementsByDate();
			request.setAttribute("achievements", achievements);
			//6
			List<Message> messages = Message.indexToUserByDate(user);
			request.setAttribute("messages", messages);
			//7
			List<Activity> friendActivities = user.getFriendActivitiesByDate();
			request.setAttribute("friendActivities", friendActivities);
			
		} catch (SQLException e) {
			System.err.println("There was an error drawing information about the current user");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp");
		dispatch.forward(request, response);
		return;
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
