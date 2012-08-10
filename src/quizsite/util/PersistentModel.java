package quizsite.util;

import java.sql.*;
import java.util.*;

/** 
 * Any model backed by a database table should implement this interface
 * */
public abstract class PersistentModel {
	private final MetaData metaData;
	
	// Saves object as a row in the table
	public abstract void save() throws SQLException;
	
	// Fetch an ArrayList of all the rows in the table
	public abstract ArrayList<PersistentModel> fetchAll() throws SQLException;
	
	// Pass in the static fields containing the meta data, while 
	public PersistentModel(MetaData meta) throws SQLException {
		this.metaData = meta;
		DatabaseConnection.createTableIfNotExists(this);
	}

	public MetaData getMetaData() {
		return metaData;
	}
	
}
