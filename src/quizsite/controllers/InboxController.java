package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizsite.models.Message;
import quizsite.models.User;

/**
 * Servlet implementation class InboxController
 */
@WebServlet({"/InboxController", "/inbox"})
public class InboxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InboxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Fetch all messages and forward to InboxView.jsp
		try {
			User currentUser = Util.signInOrRedirect(request, response);
			List<Message> received = Message.indexTo(currentUser);
			List<Message> sent = Message.indexFrom(currentUser);
			request.setAttribute(Util.RECEIVED_MSG_LIST_KEY, received);
			request.setAttribute(Util.SENT_MSG_LIST_KEY, sent);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Util.INBOX_VIEW);
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
