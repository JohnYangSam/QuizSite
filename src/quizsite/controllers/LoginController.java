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

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		//Get take in the userName and password
		//Check if the user exists with salt and password hash with in the database yet
			//Redirect them otherwise ----->>
		//Set the servlet context
		// send the user to the homepage ----->>
			
		//Get parameters from the request
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		//if (request.getAttribute("failuireMessage") == NULL don't print out anything, otherwise print out the message
	
		//Catch SQLExceptions
		try {
			if() {
				//Add user and then set the USER_SESSION_KEY to the userId
				int userId = User.;
				HttpSession session = request.getSession();
				session.setAttribute(Util.USER_SESSION_KEY, userId);
				//Send to the main view
				RequestDispatcher dispatch = request.getRequestDispatcher(Util.HOME_VIEW);
				dispatch.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("SQL Error while autheticating user registration: ");
			e.printStackTrace();
			request.setAttribute("failureMessage", "There was an error making the registration. Please try registering again.");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.REGISTER_NEW_USER_VIEW);
			dispatch.forward(request, response);

		}
	}
	
	/**
	 * Takes in userName and password Strings checks to see if they match through a salted
	 * hash. If 
	 */
	public static int userNameAndPasswordMatch(String userName, String password) {
		
	}

}
