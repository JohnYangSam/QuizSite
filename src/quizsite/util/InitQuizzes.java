package quizsite.util;

import java.io.IOException;
import java.sql.SQLException;

import org.jdom.JDOMException;

public class InitQuizzes {
	public static void createQuizzesFromXML() throws JDOMException, IOException, SQLException
	{
		XMLParser parser = new XMLParser(1);
		parser.getQuizzesFromXML("WebContent/quiz-xml/bunny.xml");
		parser.getQuizzesFromXML("WebContent/quiz-xml/cities.xml");
	}

}
