package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizsite.models.User;

public class Util {
	/** Defining all magic strings here, to avoid typos */
	public static final String USER_SESSION_KEY = "CURRENT_USER_ID";
	public static final String RECEIVED_MSG_LIST_KEY = "RECEIVED_MESSAGES";
	public static final String SENT_MSG_LIST_KEY = "SENT_MESSAGES";

	/** Views */
	public static final String LOGIN_VIEW = "LoginView.jsp";
	public static final String INBOX_VIEW = "InboxView.jsp";
	public static final String MAIN_VIEW = "MainView.jsp";
	public static final String REGISTER_NEW_USER_VIEW = "RegisterNewUserView.jsp";
	
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
	
	

	/**
	 * Returns a csv formatted string, given an object array. Uses toString(). Ignores null / objects
	 * which give only whitespace on toString()
	 * eg. 	{"abc", "def"} 	=> " abc,def "
	 * 	   	null			=> ""
	 * 		{}				=> ""
	 * 		{"abc", null, "def"} => " abc,def "
	 * 		{"a", "  ", "a"} => " a,a "
	 * @param separator TODO
	 * */
	public static String join(Object[] arr, String separator) {
		if (arr == null || arr.length == 0) {
			return " ";
		} else {
			List<Object> list = Arrays.asList(arr);
			return join(list, separator);
		}
	}



	/** See {@link Util#join(Object[], String)}
	 * @param separator TODO*/
	public static <T> String join(List<T> list, String separator) {
		if (list == null || list.size() == 0) {
			return " ";
		} else {
			StringBuilder sb = new StringBuilder(" ");
			for (Object obj : list) {
				if (obj != null && !obj.toString().trim().isEmpty()) {
					sb.append(obj.toString());
					sb.append(separator);
				}
			}
			String ret = sb.toString();
			ret = ret.substring(0, ret.length() - separator.length()); 	// Remove trailing comma
			ret = ret + " ";	// Pad 
			return ret;
		}
	}

}
