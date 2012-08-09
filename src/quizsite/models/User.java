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
public class User implements PersistentModel{
	
	private String username;
	private int userId;
	

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
