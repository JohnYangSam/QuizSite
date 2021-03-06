/**
 * File: Message.java
 * Created: Aug 6, 2012
 */
package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;

import quizsite.models.messages.Challenge;
import quizsite.models.messages.FriendRequest;
import quizsite.models.messages.Note;
import quizsite.util.DatabaseConnection;
import quizsite.util.PersistentModel;

/**
 * Subclasses, note: define a format string and call setBody() with the parameters
 * corresponding to the placeholders in the format string.
 * eg.:
 * formatString = "%1$s is before %2$s which is preceded by %1$s";
 * setBody("Vighnesh", "Rege");
 */
public abstract class Message extends PersistentModel{
	/**
	 * Usage:
	 * $ Formatter fmt = new Formatter();
	 * $ fmt.format("%1$s is before %2$s which is preceded by %1$s", "Vighnesh", "Rege"); 
	 * */
	protected Formatter fmt;
	protected String formatString;	// To be set in subclass constructor
	protected String type;

	protected User sender;
	protected User recipient;
	protected String body;	// Message body - built in subclass constructors
	protected String subject;

	// Meta data about the backing database table stored as static fields
	// to avoid copies of same information in every instantiation
	public static String TABLE_NAME = "Message";
	public static String[][] SCHEMA = {{"body", "TEXT"}, {"sender_id", "INTEGER"}, {"recipient_id", "INTEGER"}, {"type", "TINYTEXT"}, {"subject", "TINYTEXT"}};
	public final static int I_BODY = PersistentModel.N_PRE_COL, 
			I_SENDER_ID = PersistentModel.N_PRE_COL + 1, 
			I_RECIPIENT_ID = PersistentModel.N_PRE_COL + 2, 
			I_TYPE = PersistentModel.N_PRE_COL + 3,
			I_SUBJECT = PersistentModel.N_PRE_COL + 4;
	public static String[][] FOREIGN_KEYS = 
	{ {"sender_id", "User", "id"}, {"recipient_id", "User", "id"} };

	public enum Type {
		CHALLENGE, 
		FRIEND_REQUEST,
		NOTE;
		
		@Override
		public String toString() {
			return this.name();
		}
		
		public boolean equals(String type) {
			return this.toString().equals(type);
		}
		public static Message instantiate(List<String> row) throws SQLException {
			if (row != null) {
				String type = row.get(I_TYPE);
				Message curr = null;
				if (CHALLENGE.equals(type)) {
					curr = new Challenge(null, null, null);
				} else if (FRIEND_REQUEST.equals(type)) {
					curr = new FriendRequest(null, null, "");
				} else if (NOTE.equals(type)) {
					curr = new Note(null, null, "");
				} else {
					throw new IllegalArgumentException("This type doesn't exist : " + type);
				}
				curr.parse(row);	// Pops down to the right parse function
				return curr;
			} else {
				return null;
			}
		}
	};

	public Message( User sender, User recipient) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setRecipient(recipient);
		setSender(sender);
	}

	
	protected String formatBody(Object...objects) {
		// Needs a defined formatString
		fmt = new Formatter();
		return fmt.format(formatString, objects).toString();
	}

	public void setBody(String body) {
		this.body = body;
	}

	/* Generic SQL queries */

	public static List<Message> indexTo(User recipient) throws SQLException {
		String[][] conditions = { {"recipient_id", "=", "" + recipient.getId()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}
	
	public static List<Message> indexToUserByDate(User recipient) throws SQLException {
		List<Message> toUserMsgs = indexTo(recipient);
		Collections.sort(toUserMsgs, 
			new Comparator<Message>() {
				@Override
				public int compare(Message m1, Message m2) {
					return m1.getCreatedAt().compareTo(m2.getCreatedAt());
				}
			});
//TESTING Code
		for(Message msg : toUserMsgs) {
				System.err.println(msg.getCreatedAt());
			}	
		return toUserMsgs;
	}

	public static List<Message> indexFrom(User sender) throws SQLException {
		String[][] conditions = { {"sender_id", "=", ""+ sender.getId()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}

	public static List<Message> indexFromTo(User sender, User recipient) throws SQLException {
		String[][] conditions = { {"sender_id", "=", "" + sender.getId()}, {"recipient_id", "=", "" + recipient.getId()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}

	/*------------------ implement these -------------------*/
	@Override
	public Object[] getFields() {
		Object[] objs = new Object[] {getBody(), getSender().getId(), getRecipient().getId(), getType(), getSubject()};
		return objs;
	}

	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		setBody(dbEntry.get(I_BODY));
		int senderId = Integer.parseInt(dbEntry.get(I_SENDER_ID)); 
		int recipientId = Integer.parseInt(dbEntry.get(I_RECIPIENT_ID));

		User sender = User.get(senderId);
		User recipient = User.get(recipientId);

		setSender(sender);
		setRecipient(recipient);

		setType(dbEntry.get(I_TYPE));
		setSubject(dbEntry.get(I_SUBJECT));
	}

	public static List<Message> parseRows(List<List<String>> rows) throws SQLException {
		List<Message> ret = new ArrayList<Message>();
		for (List<String> row : rows) {
			Message curr = Type.instantiate(row);
			ret.add(curr);
		}
		return ret;
	}


	/** 
	 * Returns the correctly instantiated type as it's supertype - Message 
	 * Returns null if no such row exists
	 * */
	public static Message get(int id) throws SQLException {
		List<String> entry = DatabaseConnection.get(TABLE_NAME, id);
		Message curr = Type.instantiate(entry);
		return curr;
	}	
	/*------------------------------------------------------*/

	/* The usual getters and setters */
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param recipient to set
	 */
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the recipient
	 */
	public User getRecipient() {
		return recipient;
	}

	/**
	 * @param sender to set
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * @return the from
	 */
	public User getSender() {
		return sender;
	}

	@Override
	public String toString() {
		return 	"From: " + getSender().getId() + "\n" +
		"To: " + getRecipient().getId() + "\n" +
		"Body: " + getBody();
	}

	/**
	 * Use this one when reading from a database entry
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/** Use this one in constructors */
	public void setType(Type type) {
		String typ = type.toString();
		setType(typ);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getSubject() {
		return subject;
	}


	
}
