package quizsite.models.questions;

import java.sql.SQLException;

import quizsite.models.Question;

public class CheckboxQuestion extends Question {
	public CheckboxQuestion(String text) throws SQLException {
		super(text);
	}
}
