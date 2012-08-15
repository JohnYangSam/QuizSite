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
	
	@Test
	public void testSerialize()
	{
		Set<String> a = new HashSet<String>(Arrays.asList("ab","nh"));
		String serialize = Question.serializeAnswers(a);
		assertTrue("nh{!~!}ab".equals(serialize));
	}
	
	@Test
	public void testUnSerialize()
	{
		String a = "nh{!~!}ab";
		Set<String> b = Question.unserializeAnswers(a);
		int fd = 1;
	}
}
