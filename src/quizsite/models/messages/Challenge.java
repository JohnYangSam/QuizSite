/**
 * File: Challenge.java
 * Created: Aug 6, 2012
 */
package quizsite.models.messages;

import java.sql.SQLException;
import java.util.List;

import quizsite.controllers.Util;
import quizsite.models.*;
import quizsite.util.Activity;

/**
 * 
 */
public class Challenge extends Message {


	private Quiz quiz;
	
	public Challenge( User sender, User recipient, Quiz quiz) throws SQLException {
		this(sender, recipient, quiz, "Challenge");
	}
	
	public Challenge(User sender, User recipient, Quiz quiz, String subject) throws SQLException {
		super( sender, recipient );
		updateQuiz(quiz);
		setType(Type.CHALLENGE);
		setSubject(subject);
	}

	public int send() throws SQLException {
		return save();
	}



	/**
	 * @param quiz the quiz to set
	 */
	public void updateQuiz(Quiz quiz) {
		this.quiz = quiz;
		formatString = "%1$s has challenged %2$s to a %3$s";
		if (quiz != null && sender != null && recipient != null) {
			setBody( formatBody( sender.getName(), recipient.getName(), Util.wrapURL(quiz.getURL(), "quiz")) );
		}
	}


	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}


	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		// Not parsing further
		
	}


	@Override
	public Activity getActivity() {
		return new Activity(sender.getId(), sender.getName(), getCreatedAt(), "challenged ", recipient.getName() + "to take a quiz!"); 
	}
	
}
