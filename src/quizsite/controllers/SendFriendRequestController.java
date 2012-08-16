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
import quizsite.models.messages.FriendRequest;

/**
 * Servlet implementation class SendFriendRequestController
 */
@WebServlet({ "/SendFriendRequestController", "/send_friend_request" })
public class SendFriendRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendFriendRequestController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User current = Util.signInOrRedirect(request, response);
		if (current == null) return;

		Integer initiatorId = Integer.parseInt(request.getParameter("initiator_id"));
		Integer responderId = Integer.parseInt(request.getParameter("responder_id"));
		User recipient;
		try {
			if (initiatorId != null && responderId != null && initiatorId == current.getId() && (recipient = User.get(responderId)) != null) {
				(new FriendRequest(current, recipient, "url_not_used")).send();
				request.setAttribute("successMessage", "Sent");
			} else {
				throw new IllegalAccessException("Something was null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("failureMessage", "We encountered some error. Please try again later. Not sent.");
		} catch (IllegalAccessException e) {
			request.setAttribute("failureMessage", "Illegal Access. Not sent.");
		} finally {
			RequestDispatcher dispatch = request.getRequestDispatcher("_notify_message.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
