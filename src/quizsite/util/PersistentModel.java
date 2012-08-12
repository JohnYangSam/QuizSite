package quizsite.util;

import java.sql.*;
import java.util.*;

import quizsite.models.messages.Note;

/** 
 * Any model backed by a database table should extend this class
 * Additionally, add the following static methods [look up Note.java for examples]:
 * 1. public static Note get(int id) throws SQLException
 * 2. public static List<Note> index() throws SQLException
 * */
public abstract class PersistentModel {
	private final MetaData metaData;
	private int id; // TODO: Needs to be filled in after an instance is saved in the database
	
	/** Saves object as a row in the table - returns the auto generated key 
	 * @throws SQLException */
	public int save() throws SQLException  {
		int newIdx = DatabaseConnection.create(this);
		setId(newIdx);
		return getId();
	}
	
	public void delete() throws SQLException {
		DatabaseConnection.destroy(this);
	}
	
	
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
	
	
	public abstract Object[] getFields();
	
	
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

	/**
	 * @return a list of strings, where each entry is a column name
	 */
	public List<String> getColumnNames() {
		return metaData.getColumnNames();
	}

	/**
	 * @return list of values for a particular model
	 */
	public List<Object> getColumnValues() {
		return Arrays.asList(getFields());
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
