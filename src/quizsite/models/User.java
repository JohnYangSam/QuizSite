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
import quizsite.controllers.Util;
import quizsite.controllers.Util.*;

/**
 * 
 */
public class User extends PersistentModel{

	private String userName;
	private String email;
	private String passwordSaltedHash;
	private String passwordSalt;

	public static String TABLE_NAME = "User";
	public static String[][] SCHEMA = {{"user_name", "TINYTEXT"}, {"email", "TINYTEXT"}, {"password_salted_hash", "TINYTEXT"}, {"password_salt", "TINYTEXT"}};
	public final static int I_USERNAME = PersistentModel.N_PRE_COL,
							I_EMAIL = PersistentModel.N_PRE_COL + 1, 
							I_PASSWORDSALTEDHASH = PersistentModel.N_PRE_COL + 2, 
							I_PASSWORDSALT = PersistentModel.N_PRE_COL + 3;
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

	/** Returns a list of achievements of current user */
	public List<Achievement> getAchievements() throws SQLException {
		return Achievement.ofUser(this);
	}

	/** 
	 * List of all users to which the current user has sent friend requests 
	 * SELECT * FROM User WHERE id in (SELECT responder_id FROM Friendship WHERE initiator_id = <id>)
	 * @throws SQLException 
	 * */
	public List<User> sentFriendRequestsTo() throws SQLException {
		String subQ = "(" 
					+ DatabaseConnection.selectFromWhereString(
											new String[] {"responder_id"}, 
											Friendship.TABLE_NAME,
											new String[][] {{"initiator_id", "=", "" + getId()}})
					+ ")";
		String condition = "id IN " + subQ;
		return parseRows(DatabaseConnection.indexWhereRaw(TABLE_NAME, condition));
	}
	
	
	/** 
	 * List of all users who sent friend requests to the current user
	 * SELECT * FROM User WHERE id in (SELECT initiator_id FROM Friendship WHERE responder_id = <id>)
	 * @throws SQLException 
	 * */
	public List<User> gotFriendRequestsFrom() throws SQLException {
		String subQ = "(" 
					+ DatabaseConnection.selectFromWhereString(
											new String[] {"initiator_id"}, 
											Friendship.TABLE_NAME,
											new String[][] {{"responder_id", "=", "" + getId()}})
					+ ")";
		String condition = "id IN " + subQ;
		return parseRows(DatabaseConnection.indexWhereRaw(TABLE_NAME, condition));
	}
	
	/** Returns a list of ACCEPTED friendships involving current user */
	public List<User> getFriends() throws SQLException {
		List<Friendship> accepted = Friendship.indexFor(this, Friendship.Status.ACCEPTED);
		List<User> friends = Friendship.getUsersFrom(accepted);
		friends.remove(this);
		return friends;
	}
	
	/** Fetch list of users who have sent friend requests to current user, filter by given status */
	public List<User> getReceivedFriendRequests(Friendship.Status status) throws SQLException {
		List<Friendship> lf = Friendship.indexWhereResponderIs(this, status);
		List<User> freqs = Friendship.getUsersFrom(lf);
		freqs.remove(this);
		return freqs;
	}
	
	/** Compares only id's - User objects need to be saved*/
	@Override
	public boolean equals(Object user) {
		User other = (User) user;
		return (getId() == other.getId()) && getCreatedAt().equals(other.getCreatedAt()) && getUserName().equals(other.getUserName()) && getEmail().equals(other.getEmail());
	}
	
	/**
	 *  Takes in a List<List<String> > and returns a List<User> (correctly parsed)
	 * */
	private static List<User> parseRows(List<List<String> > rows) throws SQLException {
		List<User> ret = new ArrayList<User>();
		for(List<String> entry : rows) {
			//Makes a new empty user object and then populates it with with a parse of the row
			User curr;
			if (entry != null) {
				curr = new User("","","","");
				curr.parse(entry);
			} else {
				curr = null;
			}
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

	/**
	 *  Returns a user based on the inputed id
	 */
	public static User get(int id) throws SQLException {
		List<String> entry = DatabaseConnection.get(TABLE_NAME, id);
		if (entry != null) {
			User curr = new User("","","","");
			curr.parse(entry);
			return curr;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns true if the user by a given userName exists in the database.
	 * Throws an SQLException is the userName is not unique in the database (extra error checking)
	 */
	public static boolean userExists(String userName) throws SQLException {
		String[][] whereConditions = {{"user_name", "=", userName}};
		List<List<String> > entries = DatabaseConnection.indexWhere(TABLE_NAME, whereConditions);
		//Confirm that the userName is unique
		if(entries.size() > 1) throw new SQLException("more than one Use Name of the same type found: " + userName);
		if(entries.size() == 1) return true;
		return false;
	}
	
	/**
	 * Returns the user object for the name given.
	 */
	public static User getUserByName(String userName) throws SQLException {
		String[][] whereConditions = {{"user_name", "=", userName}};
		List<List<String> > entries = DatabaseConnection.indexWhere(TABLE_NAME, whereConditions);
		//Confirm that the userName is unique
		if(entries.size() > 1) throw new SQLException("more than one Use Name of the same type found: " + userName);
		if(entries.size() < 1) return null;
		List<String> entry = entries.get(0);
		if(entry != null) {
			User curr = new User("","","","");
			curr.parse(entry);
			return curr;
		} else {
			return null;
		}
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
	
	/**
	 * Registers a new user in the database (taking care of salting and hashing passwords
	 * and returns the ID of the user (that can be stored later to identify them in the session.
	 * If there is an error it returns -1.
	 */
	public static int registerNewUser(String userName, String email, String password) {
		String salt = Util.generateSalt();
		String passwordSaltedHash = Util.makeSaltedHash(password, salt);
		int userId = -1;
		try {
			//Create new user
			User newUser =  new User(userName, email, passwordSaltedHash, salt);
			//Save the user in the database
			userId = newUser.save();
		} catch (SQLException e) {
			System.err.println("Error registering user");
			e.printStackTrace();
			return -1;
		}
		return userId;
	}
	

//	@Override
//	public String toString() {
//		return "" + getName() + " " + getEmail() + " -- " + getId() ;
//	}

}
