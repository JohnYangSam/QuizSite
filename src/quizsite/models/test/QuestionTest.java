package quizsite.models.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.models.Question;
import quizsite.models.questions.ResponseQuestion;
import quizsite.util.DatabaseConnection;

public class QuestionTest {
	
	private Set<String> answers;
	private Question questObj;
	
	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		answers  = new HashSet<String>(Arrays.asList("a","b","c"));
		questObj = new ResponseQuestion(answers, "text", 1);
		DatabaseConnection.dropTablesIfExist(questObj.getTableName());
	}

	@Test
	public void testGetScore() {
		Set<String> userAnswers = new HashSet<String>(Arrays.asList("b", "a"));
		assertEquals(2, questObj.getScore(userAnswers));
		
		userAnswers = new HashSet<String>(Arrays.asList("d"));
		assertEquals(0, questObj.getScore(userAnswers));
	}

	@After
	public void tearDown() throws Exception {
		DatabaseConnection.dropTablesIfExist(questObj.getTableName());
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
	}
}
