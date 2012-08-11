package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import quizsite.util.MetaData;
import quizsite.util.PersistentModel;

public abstract class Question extends PersistentModel {
	
	protected Set<String> answers;
	protected String text;
	
	public static String TABLE_NAME = "Question";
	public static String[][] SCHEMA = {{}};
	public static String[][] FOREIGN_KEYS = 
		{ {}, {} };
	
	@Override
	public Object[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	public Question(String text) throws SQLException
	{
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		this.text = text;
	}

	@Override
	public ArrayList<PersistentModel> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Given user's answers as a set of strings, the total score will be an intersection of two sets   
	 * @param userAnswers Set of user answers
	 * @return number of matched answers
	 */
	public int getScore(Set<String> userAnswers)
	{
		int score = 0;
		for (Iterator<String> itr = userAnswers.iterator(); itr.hasNext();) {
			String answ = (String) itr.next();
			if (answers.contains(answ))
				score++;
		}
		return score;
	}
	
	public String getText()
	{ return text; }

	
}
