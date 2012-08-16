package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizsite.models.User;
import quizsite.models.messages.Note;

/**
 * Servlet implementation class SendMessageController
 */
@WebServlet({ "/SendMessageController", "/send_message" })
public class SendMessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMessageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * GET ignored here 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getParameter("to") + request.getParameter("subject") + request.getParameter("body"));
		User current = Util.signInOrRedirect(request, response);
		if (current == null) return;

		try {
			User recipient = User.getUserByName(request.getParameter("to").trim());
			String subject = request.getParameter("subject");
			String body = request.getParameter("body");
			if (recipient != null ) {
				Note newNote = new Note(current, recipient, body, subject);
				newNote.save();
				request.setAttribute("successMessage", "Sent!");
			} else {
				request.setAttribute("failureMessage", "Message delivery failure: There does not exist any user with the username: " + request.getParameter("to"));
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("_notify_message.jsp");
			dispatch.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
