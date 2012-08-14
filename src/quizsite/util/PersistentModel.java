package quizsite.util;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


/** 
 * Any model backed by a database table should extend this class
 * Additionally, add the following static methods [look up Note.java for examples]:
 * 1. public static Note get(int id) throws SQLException
 * 2. public static List<Note> index() throws SQLException
 * */
public abstract class PersistentModel {
	/** Number of pre-defined columns for every persistent model */
	public static final int N_PRE_COL = 2;
	private static final int I_ID = 0, I_CREATED_AT = 1;
	
	private final MetaData metaData;
	private int id;
	private String createdAt;
	
	/** 
	 * Saves object as a row in the table - returns the auto generated key, and sets it
	 * as the instance variable id - also sets the time stamp field 
	 * @throws SQLException */
	public int save() throws SQLException {
		int newIdx = DatabaseConnection.create(this);
		setId( newIdx );
		setCreatedAt( DatabaseConnection.get(getTableName(), newIdx).get(I_CREATED_AT) );
		return getId();
	}

	public void delete() throws SQLException {
		DatabaseConnection.destroy(this);
	}
	
	/** See {@link DatabaseConnection#update(PersistentModel)}*/
	public int update() throws SQLException {
		return DatabaseConnection.update(this);
	}

	/* NEEDS TO BE IMPLEMENTED BY SUBCLASS - before implementing call super.parse(dbEntry) */
	/** Parses the row obtained from the entry in the database and fills in the instance variables
	 * @throws SQLException 
	 * @throws IllegalArgumentException */
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		if (dbEntry.size() != getNumberOfColumns()) {
			throw new IllegalArgumentException("Number of cols in the row don't match");
		}
		// First row is id, remaining depend upon the SCHEMA order
		setId(Integer.parseInt(dbEntry.get(I_ID )));
		setCreatedAt(dbEntry.get(I_CREATED_AT));
	}

	/* NEEDS TO BE IMPLEMENTED BY SUBCLASS */
	/** 
	 * Returns an array of the fields that should be written to the database in the order
	 * that they appear in the database. For instance: a model with the instance variables:
	 * a b c d e and a relation with schema: A B C
	 * Will return a new Object[] {a, b, c}
	 * @return
	 */
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
	 * @return list of values for a particular model, according to Model#SCHEMA
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

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}


	
	
}
