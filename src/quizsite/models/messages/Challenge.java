/**
 * File: Challenge.java
 * Created: Aug 6, 2012
 */
package quizsite.models.messages;

import quizsite.models.*;

/**
 * 
 */
public class Challenge extends Message {

	private Quiz quiz;
	
	public Challenge(User recipient, User sender, Quiz quiz) {
		super(recipient, sender);
		setQuiz(quiz);
		formatString = "%1$s has challenged %2$s to a quiz %3$s";
		setBody(recipient, sender, quiz.getURL());
	}


	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	/**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

}
