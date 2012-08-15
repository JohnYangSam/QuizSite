package quizsite.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.helpers.DefaultHandler;

import quizsite.models.Quiz;
import quizsite.models.questions.CheckboxQuestion;
import quizsite.models.questions.FillBlankQuestion;
import quizsite.models.questions.PictureQuestion;
import quizsite.models.questions.RadioQuestion;
import quizsite.models.questions.ResponseQuestion;

public class XMLParser extends DefaultHandler{
	private Element rootElem;
	private int userID;
	
	public XMLParser(int userID) throws JDOMException, IOException
	{
        this.userID		   = userID;
	}
	
	public List<Quiz> getQuizzesFromXML(String src) throws JDOMException, IOException, SQLException
	{
		SAXBuilder builder = new SAXBuilder();
        Document doc 	   = builder.build(new File(src));
        rootElem	 	   = doc.getRootElement();
        
        List<Element> questions = rootElem.getChildren("question");
        List<Quiz> quizzes		= new ArrayList<Quiz>();
       
        /*
         * Settings
         */
        boolean random   			 = (rootElem.getAttribute("random").equals("true"))?true:false;
        boolean onePage 			 = (rootElem.getAttribute("one-page").equals("true"))?true:false;
        boolean practice 			 = (rootElem.getAttribute("practice-mode").equals("true"))?true:false;
        boolean immediateCorrection  = (rootElem.getAttribute("immediate-correction").equals("true"))?true:false;
        String title				 = rootElem.getChildTextTrim("title"); 
        String descr 				 = rootElem.getChildTextTrim("description");
        String category				 = rootElem.getChildTextTrim("category");
        
        Quiz newQuiz				 = new Quiz(title, descr, category, onePage, practice, immediateCorrection, random, userID);
        int newQuizID 				 = newQuiz.save();
        quizzes.add(newQuiz);
        
        for (Iterator<Element> iterator = questions.iterator(); iterator.hasNext();) {
			Element question 		 = (Element) iterator.next();
			String type		 		 = question.getAttribute("type").getValue();
			
			if (type.equals("picture-response"))
			{
				String imageUrl		= question.getChildText("image-location");
				Set<String> answers = getAnswers(question);
				(new PictureQuestion(answers, "What's in the picture?", newQuizID, imageUrl)).save();
			} else if (type.equals("fill-in-blank"))
			{
				String firstPart  	= question.getChild("blank-query").getChildText("pre");
				String secondPart= question.getChild("blank-query").getChildText("post");
				Set<String> answers	= getAnswers(question);
				(new FillBlankQuestion(answers, firstPart, secondPart, newQuizID)).save();
			} else if (type.equals("multiple-choice"))
			{
				String text = question.getChildText("query");
				
				List<String> options   = new ArrayList<String>();
				List<Element> optElems = question.getChildren("option");
				Set<String> answers	   = new HashSet<String>();
				
				for (Iterator<Element> opts  = optElems.iterator(); opts.hasNext();) {
					Element opt   = (Element) opts.next();
					options.add(opt.getValue());
					if (opt.getAttributeValue("answer") != null)
						answers.add(opt.getAttributeValue("answer"));
				}
				if (answers.size() == 1)
					(new RadioQuestion(answers, text, newQuizID, options)).save();
				else if (answers.size() > 1) (new CheckboxQuestion(answers, text, newQuizID, options)).save();
			} else if (type.equals("question-response"))
			{
				String text 		= question.getChildText("query");
				Set<String> answers	= getAnswers(question);
				(new ResponseQuestion(answers, text, newQuizID)).save();
			}
		}
        
        return quizzes;
	}
	
	private Set<String> getAnswers(Element question)
	{
		Set<String> answers		  = new HashSet<String>();
		Element answerList  	  = question.getChild("answer-list");
		if (answerList != null)
		{
			List<Element> answerElems = answerList.getChildren("answer");
			for (Iterator<Element> iterator = answerElems.iterator(); iterator.hasNext();) {
				Element crrAnsw = (Element) iterator.next();
				answers.add(crrAnsw.getValue());
			}
		} else answers.add(question.getChildText("answer"));
		return answers;
	}
	
}
