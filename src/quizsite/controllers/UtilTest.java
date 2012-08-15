package quizsite.controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UtilTest {
	private String[][] testCsvCases = {{"abc","def"}, null, {}, {"abc", null, "def"}, {"a", "  ", "a"}};
	private Object[] testCsvStrings = {" abc,def ", " ", " ", " abc,def ", " a,a "};
	private Object[] testAndStrings = {" abc AND def ", " ", " ", " abc AND def ", " a AND a "};
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCsv() {
		for (int i = 0; i < testCsvCases.length; i++) {
			assertEquals(testCsvStrings[i], Util.join(testCsvCases[i], ","));
			assertEquals(testAndStrings[i], Util.join(testCsvCases[i], " AND "));
		}
	}
	
	@Test
	public void testReadMode() {
		assertEquals("PRODUCTION", Util.readMode());
	}
}
