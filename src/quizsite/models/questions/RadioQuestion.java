package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;

public class RadioQuestion extends Question {

	public RadioQuestion(Set<String> answers, String text) throws SQLException {
		super(text, answers);
		setType(Type.RADIO);
	}

}
