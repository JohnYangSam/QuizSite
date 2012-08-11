package quizsite.util;

import java.sql.*;
import java.util.*;

/** 
 * Any model backed by a database table should implement this interface
 * */
public abstract class PersistentModel {
	private final MetaData metaData;
	private int id; // TODO: Needs to be filled in after an instance is saved in the database
	
	/**
	 * Saves object as a row in the table - returns the auto generated key
	 * @return
	 * @throws SQLException
	 */
	public abstract int save() throws SQLException;
	
	/**
	 * Fetch an ArrayList of all the rows in the table
	 * @return
	 * @throws SQLException
	 */
	public abstract ArrayList<PersistentModel> fetchAll() throws SQLException;
	
	/**
	 * 
	 * @return
	 */
	public abstract Object[] getFields();
	
	// Pass in the static fields containing the meta data, while 
	public PersistentModel(String tableName, String[][] schema, String[][] foreignKeys) throws SQLException {
		this.metaData = new MetaData(tableName, schema, foreignKeys);
		DatabaseConnection.createTableIfNotExists(this);
	}
	
	public String getTableName()
	{ return metaData.getTableName(); }
	
	public String getSchema()
	{ return metaData.getSchema(); }

	public List<ForeignKey> getForeignKeys()
	{ return metaData.getForeignKeys(); }

	public String getColumnNames() {
		return metaData.getColumnNames();
	}

	// Fetches a CSV string of all instance variable values, in the order that they appear in the schema
	public String getColumnValues() {
		StringBuilder sb = new StringBuilder();
		for (Object obj : getFields()) {
			sb.append("'");
			sb.append(obj);
			sb.append("',");
		}
		String res = sb.toString();
		return res.substring(0, res.length() - 1);		
	}
	

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
}
