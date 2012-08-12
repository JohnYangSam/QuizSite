package quizsite.util;

import java.sql.*;
import java.util.*;

/** 
 * Any model backed by a database table should implement this interface
 * */
public abstract class PersistentModel {
	private final MetaData metaData;
	private int id; // TODO: Needs to be filled in after an instance is saved in the database
	
	/** Saves object as a row in the table - returns the auto generated key */
	public abstract int save() throws SQLException;
	
	/** Fetch a List of all the rows in the table */
	public abstract List<PersistentModel> index() throws SQLException;
	
	/** Parses the row obtained from the entry in the database and fills in the instance variables
	 * @throws SQLException 
	 * @throws IllegalArgumentException */
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		if (dbEntry.size() != getNumberOfColumns()) {
			throw new IllegalArgumentException("Number of cols in the row don't match");
		}
		// First row is id, remaining depend upon the SCHEMA order
		setId(Integer.parseInt(dbEntry.get(0)));
	}
	
	
	
	/** Pass in the static fields containing the meta data, while */ 
	public PersistentModel(String tableName, String[][] schema, String[][] foreignKeys) throws SQLException {
		this.metaData = new MetaData(tableName, schema, foreignKeys);
		DatabaseConnection.createTableIfNotExists(this);
	}
	
	public String getTableName()
	{ return metaData.getTableName(); }
	
	public String getSchema()
	{ return metaData.getSchemaStringified(); }
	
	public int getNumberOfColumns()
	{ return metaData.getNumberOfColumns();}

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
	
	public abstract Object[] getFields();

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
