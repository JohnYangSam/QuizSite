package quizsite.util.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.jdom.JDOMException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.util.DatabaseConnection;
import quizsite.util.XMLParser;

public class XMLParserTest {
	
	private XMLParser parser; 

	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			parser = new XMLParser(1);
			parser.getQuizzesFromXML("WebContent/quiz-xml/bunny.xml");
			parser.getQuizzesFromXML("WebContent/quiz-xml/cities.xml");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
