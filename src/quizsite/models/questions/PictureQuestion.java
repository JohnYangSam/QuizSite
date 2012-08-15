package quizsite.models.questions;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import quizsite.models.Question;
import quizsite.util.Activity;

public class PictureQuestion extends Question {
	private String imageUrl;
	
	public PictureQuestion(Set<String> answers, String text, int quiz_id, String url) throws SQLException
	{
		super(text, answers, quiz_id);
		this.imageUrl = url;
		setType(Type.PICTURE);
	}
	
	public PictureQuestion() throws SQLException { super(); }
	
	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		
		String picUrl = dbEntry.get(I_AUXILIARY);
		setPicUrl(picUrl);
	}
	
	public String getPicUrl()
	{ return imageUrl; }
	
	public void setPicUrl(String newUrl)
	{ imageUrl = newUrl; }

	@Override
	protected String getAuxiliary() { return imageUrl; }

	@Override
	public Activity getActivity() {
		//THIS DOES NOT NEED TO BE IMPLEMENTED
		return null;
	}

}
