package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import quizsite.util.PersistentModel;

public class Quiz extends PersistentModel{

	/* Quiz settings */
	private boolean onePage;
	private boolean practice;
	private boolean immediateCheck;

	private int creatorID;
	private int quizID;

	private ArrayList<Question> questions;
	private String url;	// url to access quiz

	public static String TABLE_NAME = "Quiz";
	public static String[][] SCHEMA = {{}};
	public static String[][] FOREIGN_KEYS = 
		{ {}, {} };

	public Quiz(boolean onePage, boolean practice, boolean immediateCheck, int creatorID, int quizID, ArrayList<Question> questions) throws SQLException
	{
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
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

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<PersistentModel> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}

}
