package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizsite.models.User;

public class Util {
	/** Defining all magic strings here, to avoid typos */
	public static final String USER_SESSION_KEY = "CURRENT_USER_ID";
	public static final String LOGIN_VIEW = "LoginView.jsp";
	public static final String RECEIVED_MSG_LIST_KEY = "RECEIVED_MESSAGES";
	public static final String SENT_MSG_LIST_KEY = "SENT_MESSAGES";
	public static final String INBOX_VIEW = "InboxView.jsp";
	
	/** 
	 * Return currently logged in user if user-session exists and if the user-session belongs to a valid user
	 * Else redirect to login page
	 * @throws IOException
	 * @throws ServletException 
	 * */
	public static User signInOrRedirect(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute(USER_SESSION_KEY);
		User curr;
		if ( userId != null && (curr = User.get(userId)) != null) {
			return curr;
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher(LOGIN_VIEW);
			dispatch.forward(request, response);
		}
		return null;
	}
	

}
