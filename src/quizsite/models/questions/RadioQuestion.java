package quizsite.models.questions;

import java.sql.SQLException;

import quizsite.models.Question;

public class RadioQuestion extends Question {

	public RadioQuestion(String text) throws SQLException {
		super(text);
	}

}
