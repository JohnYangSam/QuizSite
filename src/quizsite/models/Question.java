package quizsite.models;

import java.util.Iterator;
import java.util.Set;

public abstract class Question {
	protected Set<String> answers;
	protected String text;
	
	/**
	 *  
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
	{
		return text;
	}
	
	public void setText(String newText)
	{
		text = newText;
	}
}
