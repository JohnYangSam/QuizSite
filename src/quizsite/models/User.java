/**
 * File: User.java
 * Created: Aug 6, 2012
 */
package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;

import quizsite.util.PersistentModel;

/**
 * 
 */
public class User extends PersistentModel{
	
	private String username;
	private int userId;
	
	protected static String TABLE_NAME = "User";
	protected static String SCHEMA = "";
	protected static String[][] FOREIGN_KEYS = 
		{{}, {}};
	
	public User() throws SQLException
	{
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
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

}
