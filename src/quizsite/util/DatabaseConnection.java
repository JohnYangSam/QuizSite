/**
 * File: DatabaseConnection.java
 * Created: Aug 6, 2012
 */
package quizsite.util;

import java.sql.*;
import java.util.*;

/**
 * File: DBConnection.java
 * Author: Rege
 * Created: Jul 26, 2012
 * eg.
 * $ try {
 * $	DatabaseConnection db = new DatabaseConnection();
 * $ 	db.executeQuery();
 * $	db.close();
 * $ } catch (SQLException e) {}
 */
public class DatabaseConnection {
	private static String account = "ccs108rege";  
	private static String password = "ahdeetha"; 
	private static String server = "mysql-user.stanford.edu";
	private static String database = "c_cs108_rege";

//	Single DatabaseConnection instance is used for all database tables
//	so we don't store tablename here.
	private Statement stmt;
	private Connection conn;

	/**
	 * Private helper: Sets up connection to the database. Catches ClassNotFoundException and throws {@link SQLException}
	 * @return statement variable to be used for executing queries.
	 * */
	private void setUpDBConnection() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DriverManager.getConnection ( "jdbc:mysql://" + server, account ,password);
			stmt = conn.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses the ResultSet variable obtained from a SQL query and returns it as a ArrayList 
	 * of rows [each row is a ArrayList of Strings]. Throws {@link SQLException}
	 * @param	rs	{@link ResultSet} variable obtained from a SQL query
	 * @return	a ArrayList of ArrayList of Strings containing all the rows contained in the parameter 
	 * */
	public static ArrayList< ArrayList<String> > parseResultData( ResultSet rs ) throws SQLException {
		// Fill in the data
		ArrayList< ArrayList<String> > rows = new ArrayList< ArrayList<String> >();
		ArrayList<String> newRow;
		int nCol = rs.getMetaData().getColumnCount();
		while(rs.next()) {
			newRow = new ArrayList<String>();
			for (int c = 0; c < nCol; c++) {
				newRow.add(rs.getString(c + 1));			// Numbering is 1-indexed			
			}
			rows.add(newRow);
		}
		return rows;
	}
	
	/**
	 * Used to execute raw SQL queries
	 */
	public ResultSet executeQuery(String sqlQuery) throws SQLException {
		return stmt.executeQuery(sqlQuery);
	}
	
	/**
	 * Execute updates on the table - INSERT, UPDATE, DELETE
	 * */
	public int executeUpdate(String sqlQuery) throws SQLException {
		return stmt.executeUpdate(sqlQuery);
	}

	
	/**
	 * Constructor
	 * */
	public DatabaseConnection() throws SQLException {
		setUpDBConnection();
	}
	
	// Closes the JDBC connection - call it once you're done with your db object
	public void close() throws SQLException {
		conn.close();
	}
	
	/*** STANDARD SQL QUERIES ***/
	
	/**
	 * Fetches all rows from given table as a ArrayList of ArrayList of strings
	 * */
	public ArrayList< ArrayList<String> > fetchAllRows(String tablename) throws SQLException {
		ResultSet rs = executeQuery("SELECT * FROM " + tablename);
		return parseResultData(rs);
	}

	// Creates the backing table if it doesn't exist
	public int createTableIfNotExists(PersistentModel pm) throws SQLException {
		String createTableQuery = "CREATE TABLE IF NOT EXISTS " + pm.getTableName() + "( " + pm.getSchema() + ForeignKey.serialize(pm.getForeignKeys()) + " ) ";
		return executeUpdate(createTableQuery);
	}
	
	/* REST API */
	// Get a list of all rows
	public static ArrayList<ArrayList<String> > index(String tablename) throws SQLException {
		DatabaseConnection db = new DatabaseConnection();
		ArrayList<ArrayList<String> > ret = db.fetchAllRows(tablename);
		db.close();
		return ret;
	}
	
	// Create and save a new row
	public static void create(PersistentModel pm) {
		String tableName = pm.getTableName();
	}
	
	

}
