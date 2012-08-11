package quizsite.models.questions;

import java.sql.SQLException;
import java.util.Set;

import quizsite.models.Question;

public class PictureQuestion extends Question {
	private String imageUrl;
	
	public PictureQuestion(Set<String> answers, String text, String url) throws SQLException
	{
		super(text, answers);
		this.imageUrl = url;
	}
	
	public String getUrl()
	{ return imageUrl; }

}
