package quizsite.util;

import java.sql.*;
import java.util.*;

/** 
 * Any model backed by a database table should implement this interface
 * */
public abstract class PersistentModel {
	protected String tableName ;
	protected String schema ;
	protected ForeignKey[] foreignKeys;
	
	public String getTableName() {
		return tableName;
	}

	public String getSchema() {
		return schema;
	}

	public ForeignKey[] getForeignKeys() {
		return foreignKeys;
	}

	// Saves object as a row in the table
	public abstract void save() throws SQLException;
	
	// Fetch an ArrayList of all the rows in the table
	public abstract ArrayList<Object> fetchAll() throws SQLException;	

}
