package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quizsite.util.PersistentModel;

public class Attempt extends PersistentModel {
	private Quiz quiz;
	private int score;
	private User attempter;

	protected static String TABLE_NAME = "Attempt";
	protected static String[][] SCHEMA = 
		{{ "quiz_id", "INTEGER"}, {"attempter_id", "INTEGER"}, {"score", "INTEGER"}};

	protected static String[][] FOREIGN_KEYS = 
		{{ "quiz_id", "Quiz", "id"}, {"attempter_id", "User", "id"}};
	
	
	public Attempt(Quiz quiz, int score, User attempter) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
	}


	/** METHODS TO COMMUNICATE WITH THE DATABASE */
	public static List<Attempt> index() throws SQLException {
		
		return null;
	}


	@Override
	public Object[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}


	public static PersistentModel get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void parse(List<String> dbEntry) {
		// TODO Auto-generated method stub
		
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setAttempter(User attempter) {
		this.attempter = attempter;
	}

	public User getAttempter() {
		return attempter;
	}
	
}
