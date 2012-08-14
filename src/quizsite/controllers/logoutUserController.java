package quizsite.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logoutUserController
 */
@WebServlet("/logoutUserController")
public class logoutUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logoutUserController() {
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
		String userId = (String)session.getAttribute(Util.USER_SESSION_KEY);
		if(userId == null) {
			request.setAttribute("failureMessage","You are already logged out");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.HOME_CONTROLLER);
			dispatch.forward(request, response);
			return;
		} else {
			request.setAttribute("failureMessage","You are logged out");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.HOME_CONTROLLER);
			//Remove the userId attribute from the session to successfully logout
			session.removeAttribute(Util.USER_SESSION_KEY);
			dispatch.forward(request, response);
			return;
		}
		// TODO Auto-generated method stub
	}

}
