package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;
import quizsite.util.Activity;

public class ResponseQuestion extends Question {

	public ResponseQuestion(Set<String> answers, String text, int quiz_id) throws SQLException {
		super(text, answers, quiz_id);
		setType(Type.RESPONSE);
	}
	
	public ResponseQuestion() throws SQLException { super(); }

	@Override
	protected String getAuxiliary() { return null; }

	@Override
	public Activity getActivity() {
		//THIS DOES NOT NEED TO BE IMPLEMENTED
		return null;
	}

}
