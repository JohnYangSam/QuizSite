package quizsite.util;

import java.sql.*;
import java.util.*;

/** 
 * Any model backed by a database table should implement this interface
 * */
public interface PersistentModel {
	
	// Saves object as a row in the table
	public void save() throws SQLException;
	
	// Fetch an ArrayList of all the rows in the table
	public ArrayList<Object> fetchAll() throws SQLException;	

}
