/**
 * File: Message.java
 * Created: Aug 6, 2012
 */
package quizsite.models;

import java.sql.SQLException;
import java.util.*;

import quizsite.util.ForeignKey;
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
	
	private User sender;
	private User recipient;
	protected String body;	// Message body - built in subclass constructors
	
	protected String tableName = "Message";
	protected String schema = "body TEXT, sender_id INTEGER, recipient_id INTEGER";
	protected ForeignKey[] foreignKeys = 
		{new ForeignKey("sender_id", "User", "id"), new ForeignKey("recipient_id", "User", "id")};


	public Message(User recipient, User sender) {
		this.setRecipient(recipient);
		this.setSender(sender);
		this.fmt = new Formatter();
	}

	protected void setBody(Object...objects) {
		// Needs a defined formatString
		body = fmt.format(formatString, objects).toString();
	}
	
	/* Generic SQL queries */
	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Object> fetchAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
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
}
