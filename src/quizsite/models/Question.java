package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import quizsite.util.PersistentModel;

public abstract class Question implements PersistentModel {
	
	protected Set<String> answers;
	protected String text;
	
	public Question(String text)
	{
		this.text = text;
	}

	@Override
	public ArrayList<Object> fetchAll() throws SQLException {
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
