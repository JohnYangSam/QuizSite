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
 */
public class DatabaseConnection {
	private static String account = "ccs108rege";  
	private static String password = "ahdeetha"; 
	private static String server = "mysql-user.stanford.edu";
	private static String database = "c_cs108_rege";

//	Single DatabaseConnection instance is used for all database tables
//	so we don't store tablename here.
	
	private Statement stmt;

	/**
	 * Private helper: Sets up connection to the database. Catches ClassNotFoundException and throws {@link SQLException}
	 * @return statement variable to be used for executing queries.
	 * */
	private void setUpDBConnection() throws SQLException{
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con = 
				DriverManager.getConnection ( "jdbc:mysql://" + server, account ,password);
			stmt = con.createStatement();
			stmt.executeQuery("USE " + database);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses the ResultSet variable obtained from a SQL query and returns it as a vector 
	 * of rows [each row is a vector of Strings]. Throws {@link SQLException}
	 * @param	rs	{@link ResultSet} variable obtained from a SQL query
	 * @return	a Vector of Vector of Strings containing all the rows contained in the parameter 
	 * */
	public static Vector< Vector<String> > parseResultData( ResultSet rs ) throws SQLException {
		// Fill in the data
		Vector< Vector<String> > rows = new Vector< Vector<String> >();
		Vector<String> newRow;
		int nCol = rs.getMetaData().getColumnCount();
		while(rs.next()) {
			newRow = new Vector<String>();
			for (int c = 0; c < nCol; c++) {
				newRow.add(rs.getString(c + 1));			// Numbering is 1-indexed			
			}
			rows.add(newRow);
		}
		return rows;
	}
	
	public ResultSet executeQuery(String sqlQuery) throws SQLException {
		return stmt.executeQuery(sqlQuery);
	}

	public Vector< Vector<String> > getAllRows(String tablename) throws SQLException {
		ResultSet rs = executeQuery("SELECT * FROM " + tablename);
		return parseResultData(rs);
	}

	public DatabaseConnection() {
		try {
			setUpDBConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
