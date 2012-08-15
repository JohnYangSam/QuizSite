package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import quizsite.util.Activity;
import quizsite.util.DatabaseConnection;
import quizsite.util.PersistentModel;

public class Attempt extends PersistentModel {
	private Quiz quiz;
	private int score;
	private User attempter;

	public static String TABLE_NAME = "Attempt";
	protected static String[][] SCHEMA = 
		{{ "quiz_id", "INTEGER"}, {"attempter_id", "INTEGER"}, {"score", "INTEGER"}};
	public static int 	I_QUIZ_ID = PersistentModel.N_PRE_COL,
						I_ATTEMPTER_ID = PersistentModel.N_PRE_COL + 1,
						I_SCORE = PersistentModel.N_PRE_COL + 2;

	protected static String[][] FOREIGN_KEYS = 
		{{ "quiz_id", "Quiz", "id"}, {"attempter_id", "User", "id"}};

	public Attempt(Quiz quiz, User attempter, int score) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setQuiz(quiz);
		setAttempter(attempter);
		setScore(score);
	}

	/** METHODS TO COMMUNICATE WITH THE DATABASE */
	public static List<Attempt> index() throws SQLException {
		return parseRows(DatabaseConnection.index(TABLE_NAME));
	}
	
	public static List<Attempt> ofUser(User user) throws SQLException {
		String[][] conditions = {{"attempter_id", "=", "" + user.getId()}};
		return parseRows(DatabaseConnection.indexWhere(TABLE_NAME, conditions));
	}
	
	public static List<Attempt> ofUserByDate(User user) throws SQLException {
		List<Attempt> attemptsList = ofUser(user);
		Collections.sort(attemptsList,
			new Comparator<Attempt>() {
				@Override
				public int compare(Attempt o1, Attempt o2) {
					return o1.getCreatedAt().compareTo(o2.getCreatedAt());
				}
		});
		return attemptsList;
	}
	
	public static List<Attempt> ofUserAtQuiz(User user, Quiz quiz) throws SQLException {
		String[][] conditions = {{"attempter_id", "=", "" + user.getId()}, {"quiz_id", "=", "" + quiz.getId()}};
		return parseRows(DatabaseConnection.indexWhere(TABLE_NAME, conditions));
	}
	
	public static List<Attempt> atQuiz(Quiz quiz) throws SQLException {
		String[][] conditions = {{"quiz_id", "=", "" + quiz.getId()}};
		return parseRows(DatabaseConnection.indexWhere(TABLE_NAME, conditions));
	}

	
	/** Returns the top scoring attempt from the given list of attempts [need not belong to same quiz]
	 * Club this with the index methods above to achieve the desired effect */
	public static Attempt getTopScoring(List<Attempt> attempts) {
		if (attempts == null) return null;
		Attempt best = attempts.get(0);
		int top = best.getScore();
		for (Attempt atmpt : attempts) {
			if (atmpt.getScore() >= top) {
				best = atmpt;
				top = atmpt.getScore();
			}
		}
		return best;
	}

	

	@Override
	public Object[] getFields() {
		return new Object[] {getQuiz().getId(), getAttempter().getId(), getScore()};
	}

	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		setQuiz(Quiz.get(Integer.parseInt(dbEntry.get(I_QUIZ_ID))));
		setAttempter(User.get(Integer.parseInt(dbEntry.get(I_ATTEMPTER_ID))));
		setScore(Integer.parseInt(dbEntry.get(I_SCORE)));
	}


	public static List<Attempt> parseRows(List< List<String> > rows) throws SQLException {
		List<Attempt> ret = new ArrayList<Attempt>();
		for (List<String> row : rows) {
			if (row != null) {
				Attempt curr = new Attempt(null, null, 0);
				curr.parse(row);
				ret.add(curr);
			} else {
				ret.add(null);
			}
		}
		return ret;
	}

	public static Attempt get(int id) throws SQLException {
		List<String> entry = DatabaseConnection.get(TABLE_NAME, id);
		if (entry != null) {
			Attempt curr = new Attempt(null, null, 0);
			curr.parse(entry);
			return curr;
		} else {
			return null;
		}
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

	@Override
	public Activity getActivity() {
		return new Activity(attempter.getName(), getCreatedAt(), "scored " + score + " on", quiz.getTitle());
	}
	
}
