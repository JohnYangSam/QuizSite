package quizsite.models.questions;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import quizsite.models.Question;
import quizsite.util.Activity;

public class FillBlankQuestion extends Question {
	
	/*
	 * firstPart + user_input_field + secondPart
	 */
	private String firstPart;
	private String secondPart;
	
	public FillBlankQuestion(Set<String> answers, String first, String second, int quiz_id) throws SQLException {
		super("Fill in the blank field.", answers, quiz_id);
		this.firstPart  = first;
		this.secondPart = second;
		setType(Type.FILL_BLANK);
	}
	
	public FillBlankQuestion() throws SQLException { super(); }
	
	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		String[] firstSecond = unserialize(dbEntry.get(I_AUXILIARY));
		
		setFirst(firstSecond[0]);
		setSecond(firstSecond[1]);
	}
	
	public void setFirst(String newFirst)
	{ firstPart = newFirst; }
	
	public void setSecond(String newSecond)
	{ secondPart = newSecond; }

	public String getFirstPart()
	{ return firstPart; }
	
	public String getSecondPart()
	{ return secondPart; }

	@Override
	protected String getAuxiliary() { return firstPart + "{!~!}" + secondPart; }
	
	private String[] unserialize(String firstSecond)
	{
		String[] data = firstSecond.split("{!~!}");
		return data;
	}

	@Override
	public Activity getActivity() {
		// THIS DOES NOT NEED AN ACTIVITY METHOD
		return null;
	}
}
