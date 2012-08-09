package quizsite.models.questions;

import quizsite.models.Question;

public class PictureQuestion extends Question {
	private String imageUrl;
	
	public PictureQuestion(String text, String url)
	{
		super(text);
		this.imageUrl = url;
	}
	
	public String getUrl()
	{ return imageUrl; }

}
