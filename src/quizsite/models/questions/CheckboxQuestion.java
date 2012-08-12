package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;

public class CheckboxQuestion extends Question {
	public CheckboxQuestion(Set<String> answers, String text) throws SQLException {
		super(text, answers);
		setType(Type.CHECKBOX);
	}
}
