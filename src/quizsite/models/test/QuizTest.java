package quizsite.models.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.sun.jmx.remote.internal.ArrayNotificationBuffer;

import quizsite.models.Question;
import quizsite.models.Quiz;
import quizsite.models.questions.FillBlankQuestion;
import quizsite.models.questions.PictureQuestion;

public class QuizTest {
	
	private Quiz quizObj;
	private boolean onePage = false;
	private boolean practice = false;
	private boolean immediateCheck = false;
	private boolean randomized = true;
	private ArrayList<Question> questions;

	@Before
	public void setUp() throws Exception {
		quizObj = new Quiz(onePage, practice, immediateCheck, randomized, 0, 0, questions);
	}


}
