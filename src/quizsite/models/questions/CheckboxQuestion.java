package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;

public class CheckboxQuestion extends Question {
	public CheckboxQuestion(Set<String> answers, String text, int quiz_id) throws SQLException {
		super(text, answers, quiz_id);
		setType(Type.CHECKBOX);
	}
	
	public CheckboxQuestion() throws SQLException { super(); }

	@Override
	protected String getAuxiliary() { return null; }
}
