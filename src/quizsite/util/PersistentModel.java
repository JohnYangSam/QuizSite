package quizsite.util;

import java.sql.*;
import java.util.*;

/** 
 * This superclass should be extended by any model backed by a database table
 * */
public interface PersistentModel {
	
	// Saves object as a row in the table
	public void save() throws SQLException;
	
	// Fetch an ArrayList of all the rows in the table
	public ArrayList<Object> fetchAll() throws SQLException;	

}
