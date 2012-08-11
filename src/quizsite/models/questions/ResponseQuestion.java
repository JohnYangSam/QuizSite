package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;

public class ResponseQuestion extends Question {

	public ResponseQuestion(Set<String> answers, String text) throws SQLException {
		super(text, answers);
	}

}
