package quizsite.models;

import java.util.ArrayList;

public class Quiz {
	
	/* Quiz settings */
	private boolean onePage;
	private boolean practice;
	private boolean immediateCheck;
	
	private int creatorID;
	private int quizID;
	
	private ArrayList<Question> questions;
	private String url;	// url to access quiz
	
	public Quiz(boolean onePage, boolean practice, boolean immediateCheck, int creatorID, int quizID, ArrayList<Question> questions)
	{
		this.onePage	 	= onePage;
		this.practice 	 	= practice;
		this.immediateCheck = immediateCheck;
		this.creatorID 		= creatorID;
		this.quizID 		= quizID;
		this.questions		= questions;
	}
	
	public boolean isOnePage()
	{ return onePage; }
	
	public boolean isPracticeEnabled()
	{ return practice; }
	
	public boolean isImmediate()
	{ return immediateCheck; }
	
	public int getCreatorID()
	{ return creatorID; }

	public int getQuizID()
	{ return quizID; }
	
	public ArrayList<Question> getQustions()
	{ return questions; }

	// URL to access quiz
	public String getURL() {
		return url;
	}

}
