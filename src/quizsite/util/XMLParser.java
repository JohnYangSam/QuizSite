package quizsite.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.helpers.DefaultHandler;

import quizsite.models.Quiz;

public class XMLParser extends DefaultHandler{
/*	//private HashMap<String, ArrayList<Card>> data = new HashMap<String, ArrayList<Card>>();
	private List<Quiz> quizzes = new ArrayList<Quiz>();
	
	public List<Quiz> parseXML(String src) throws JDOMException, IOException
	{
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(src));
        Element rootElem = doc.getRootElement();
        List<Element> cards = rootElem.getChildren("card");
        String word = "";
        String synonym = "";
        String lessonID = "";
        for (int i = 0; i < cards.size(); i++) {
        	word = cards.get(i).getChild("word").getTextTrim();
        	synonym = cards.get(i).getChild("synonym").getTextTrim();
        	lessonID = cards.get(i).getAttribute("lesson").getValue();
        	Card newCard = new Card(word, synonym, lessonID, new ArrayList<String>());
        	if (!data.containsKey(lessonID))
        		data.put(lessonID, new ArrayList<Card>());
        	data.get(lessonID).add(newCard);
		}
        
		return data;
	}
	
	public void addWord(Card card, String src) throws JDOMException, IOException
	{
		SAXBuilder builder = new SAXBuilder();  // parameters control validation, etc
		Document doc = builder.build(new File(src));
		// Create a card element
		Element cardElem = new Element("card");
		cardElem.setAttribute("lesson", card.getLessonID());
		Element wordElem = new Element("word").setText(card.getWord());
		Element synonym = new Element("synonym").setText(card.getSynonym());
		// Add the kids
		cardElem.addContent(wordElem);
		cardElem.addContent(synonym);
		doc.getRootElement().addContent(cardElem);
		XMLOutputter outputter1 = new XMLOutputter(Format
			    .getPrettyFormat());
			      outputter1.output(doc, new FileWriter(src));
	}
	*/
}
