package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizsite.models.User;
import quizsite.controllers.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet({"/LoginUserController", "/login"})
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserController() {
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
		HttpSession session = request.getSession();
		Integer sessionUserId = (Integer) session.getAttribute(Util.USER_SESSION_KEY);
		if(sessionUserId != null) {
			request.setAttribute("failureMessage", "You are logged in. Would you like to log out?");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.LOGOUT_VIEW);
			dispatch.forward(request, response);
			return;	
		}
		
		
		//Get take in the userName and password
		//Check if the user exists with salt and password hash with in the database yet
			//Redirect them otherwise ----->>
		//Set the servlet context
		// send the user to the homepage ----->>
			
		//Get parameters from the request
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//if (request.getAttribute("failuireMessage") == NULL don't print out anything, otherwise print out the message
		//Check empty password
		if(password.equals("")) {
			request.setAttribute("failureMessage", "Invalid user name or password. Please retry logging in.");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.LOGIN_VIEW);
			dispatch.forward(request, response);
			return;
		}	
		//Catch SQLExceptions
		try {
		
			//If the user is authenticated
			if(userNameAndPasswordMatch(userName, password)) {
				//then set the USER_SESSION_KEY to the userId
				Integer userId = User.getUserByName(userName).getId();
				session.setAttribute(Util.USER_SESSION_KEY, userId);
			
				//TEST CODE
				System.out.println("userId being sent to session at login: " + userId);
				
				//Send to the main view
				RequestDispatcher dispatch = request.getRequestDispatcher("home");
				dispatch.forward(request, response);
				return;
			}
		} catch (SQLException e) {
			System.err.println("SQL Error while autheticating user registration: ");
			e.printStackTrace();
			request.setAttribute("failureMessage", "There was an error Logging in. Please try logging in again.");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.LOGIN_VIEW);
			dispatch.forward(request, response);
			return;
		}
		//If the credentials are not met
		request.setAttribute("failureMessage", "Invalid user name or password. Please retry logging in.");
		
		//TESTING CODE
		System.out.println("Mis match password? ");
		
		RequestDispatcher dispatch = request.getRequestDispatcher(Util.LOGIN_VIEW);
		dispatch.forward(request, response);
		return;
	}
	
	/**
	 * Takes in userName and password Strings checks to see if they match through a salted
	 * hash. If 
	 */
	public static boolean userNameAndPasswordMatch(String userName, String password) {
		try {
			User user = User.getUserByName(userName);
			String salt = user.getPasswordSalt();
			String saltedHash = Util.makeSaltedHash(password, salt);
		
			//TEST CODE
			System.err.println(saltedHash);
			
			return user.getPasswordSaltedHash().equals(saltedHash);
		} catch (Exception e) {
			System.err.println("SQL Error checking userName and password match");
			e.printStackTrace();
			return false;
		}
	}

}
