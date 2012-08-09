package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;

import quizsite.util.DatabaseConnection;
import quizsite.util.ForeignKey;
import quizsite.util.PersistentModel;

public class Attempt extends PersistentModel {
	private Quiz quiz;
	private int score;
	private User attempter;

	
	protected String tableName = "Attempt";
	protected String schema = "id int, quiz_id int, attempter_id int, score int";
	protected ForeignKey[] foreignKeys = 
		{new ForeignKey("quiz_id", "Quiz", "id"), new ForeignKey("attempter_id", "User", "id")};
 
		
	public Attempt(Quiz quiz, int score, User attempter) {
		setQuiz(quiz);
		setScore(score);
		setAttempter(attempter);
	}

	
	/*** STANDARD SETTERS AND GETTERS ***/
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

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param attempter the attempter to set
	 */
	public void setAttempter(User attempter) {
		this.attempter = attempter;
	}

	/**
	 * @return the attempter
	 */
	public User getAttempter() {
		return attempter;
	}

	
	/*** METHODS TO COMMUNICATE WITH THE DATABASE ***/
	@Override
	public void save() throws SQLException {
		
		
	}


	@Override
	public ArrayList<Object> fetchAll() throws SQLException {
		
		return null;
	}
	
	
}
