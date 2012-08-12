/**
 * File: User.java
 * Created: Aug 6, 2012
 */
package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quizsite.util.PersistentModel;

/**
 * 
 */
public class User extends PersistentModel{

	private String username;
	
	protected static String TABLE_NAME = "User";
	protected static String[][] SCHEMA = {};
	protected static String[][] FOREIGN_KEYS = {};
	
	public User() throws SQLException
	{
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
	}
	
	// Temp one for testing purposes - MessageTest/rege
	public User(int id) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setId(id);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save() throws SQLException {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<PersistentModel> index() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}


	public static User get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parse(List<String> dbEntry) {
		// TODO Auto-generated method stub
		
	}


}
