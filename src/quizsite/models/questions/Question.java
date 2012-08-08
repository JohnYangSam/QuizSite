package quizsite.models.questions;

import java.util.Iterator;
import java.util.Set;

public abstract class Question {
	
	protected Set<String> answers;
	protected String text;
	
	public Question(String text)
	{
		this.text = text;
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
