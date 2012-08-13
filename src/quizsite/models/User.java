/**
 * File: User.java
 * Created: Aug 6, 2012
 */
package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quizsite.models.Message.Type;
import quizsite.util.DatabaseConnection;
import quizsite.util.PersistentModel;

/**
 * 
 */
public class User extends PersistentModel{

	private String userName;
	private String email;
	private String passwordSaltedHash;
	private String passwordSalt;
	
	public static String TABLE_NAME = "User";
	public static String[][] SCHEMA = {{"name", "VARCHAR"}, {"email","VARCHAR"}, {"passwordSaltedHash", "VARCHAR"}, {"passwordSalt", "VARCHAR"}};
	public final static int I_USERNAME = 1, I_EMAIL = 2, I_PASSWORDSALTEDHASH = 3, I_PASSWORDSALT = 4;
	protected static String[][] FOREIGN_KEYS = {};
	
	public User(String userName, String email, String passwordSaltedHash, String passwordSalt) throws SQLException
	{
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		this.setUserName(userName);
		this.setEmail(email);
		this.setPasswordSaltedHash(passwordSaltedHash);
		this.setPasswordSalt(passwordSalt);
	}
	
	// Temp one for testing purposes - MessageTest/rege
	public User(int id) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setId(id);
	}

	/* Returns the name of the user */
	public String getName() {
		return getUserName();
	}

	/* save() is already implemented by the 2 */

	/*
	 * Returns a list of the users in the database. Each user is represented
	 * as a User instance.
	 */
	public static List<User> index() throws SQLException {
		List<List<String> > rows = DatabaseConnection.index(TABLE_NAME);
		return parseRows(rows);
	}

	private static List<User> parseRows(List<List<String> > rows) throws SQLException {
		List<User> ret = new ArrayList<User>();
		for(List<String> row : rows) {
			//Makes a new empty user object and then populates it with with a parse of the row
			User curr = new User("", "", "", "");
			curr.parse(row);
			ret.add(curr);
		}
		return ret;
	}

	/**
	 * Returns the instance variables of the user instance in the order they
	 * appear in the database schema.
	 */
	@Override
	public Object[] getFields() {
		return new Object[] {getName(), getEmail(), getPasswordSaltedHash(), getPasswordSalt()};
	}


	public static User get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Parses a list of strings representing the values in a dbEntry and 
	 * populates the user instance variables with those values in order
	 * @throws SQLException 
	 * @throws IllegalArgumentException 
	 */
	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry); //This takes care of the id which is the first attribute for 
		setUserName(dbEntry.get(I_USERNAME));
		setEmail(dbEntry.get(I_EMAIL));
		setPasswordSaltedHash(dbEntry.get(I_PASSWORDSALTEDHASH));
		setPasswordSalt(dbEntry.get(I_PASSWORDSALT));
	}
	
	/* ----------------- Getters and setters ----------------------- */
	
	/* userName */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	/* email */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	/* passwordSaltedHash */
	public void setPasswordSaltedHash(String passwordSaltedHash) {
		this.passwordSaltedHash = passwordSaltedHash;
	}
	
	public String getPasswordSaltedHash() {
		return this.passwordSaltedHash;
	}

	/* passwordSalt */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

}
