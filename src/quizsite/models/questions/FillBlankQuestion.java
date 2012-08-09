package quizsite.models.questions;

import quizsite.models.Question;

public class FillBlankQuestion extends Question {
	
	/*
	 * firstPart + user_input_field + secondPart
	 */
	private String firstPart;
	private String secondPart;
	
	public FillBlankQuestion(String first, String second) {
		super("Fill in the blank field.");
		this.firstPart  = first;
		this.secondPart = second; 
	}

	public String getFirstPart()
	{ return firstPart; }
	
	public String getSecondPart()
	{ return secondPart; }
}
