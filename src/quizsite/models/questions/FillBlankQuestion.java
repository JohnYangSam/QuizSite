package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;

public class FillBlankQuestion extends Question {
	
	/*
	 * firstPart + user_input_field + secondPart
	 */
	private String firstPart;
	private String secondPart;
	
	public FillBlankQuestion(Set<String> answers, String first, String second) throws SQLException {
		super("Fill in the blank field.", answers);
		this.firstPart  = first;
		this.secondPart = second;
		setType(Type.FILL_BLANK);
	}

	public String getFirstPart()
	{ return firstPart; }
	
	public String getSecondPart()
	{ return secondPart; }
}
