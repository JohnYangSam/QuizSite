package quizsite.models.questions;

import java.sql.SQLException;

import quizsite.models.Question;

public class ResponseQuestion extends Question {

	public ResponseQuestion(String text) throws SQLException {
		super(text);
	}

}
