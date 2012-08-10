package quizsite.models.questions;

import java.sql.SQLException;

import quizsite.models.Question;

public class PictureQuestion extends Question {
	private String imageUrl;
	
	public PictureQuestion(String text, String url) throws SQLException
	{
		super(text);
		this.imageUrl = url;
	}
	
	public String getUrl()
	{ return imageUrl; }

}
