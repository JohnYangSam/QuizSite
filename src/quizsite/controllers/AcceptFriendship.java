package quizsite.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizsite.models.Friendship;
import quizsite.models.User;

/**
 * Servlet implementation class AcceptFriendship
 */
@WebServlet({ "/AcceptFriendship", "/accept_friendship" })
public class AcceptFriendship extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptFriendship() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Friendship f = Friendship.get(Integer.parseInt(request.getParameter("friendship_id")));
			User current = Util.signInOrRedirect(request, response);
			if (current == null) return;
			if (current.getId() == f.getResponder().getId()) {
				f.accept();
				request.setAttribute("successMessage", "You accepted " + f.getInitiator().getName() + "'s friend request, " +  f.getResponder().getName() + ". Hallelujah! Hallelujah!");
			} else {
				request.setAttribute("failureMessage", "You cannot accept a friend request not sent to you.");
			}
			RequestDispatcher dispatch = request.getRequestDispatcher("accept_friendship.jsp");
			dispatch.forward(request, response);
		} catch (SQLException e) {}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
