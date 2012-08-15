/**
 * File: FriendRequest.java
 * Created: Aug 6, 2012
 */
package quizsite.models.messages;

import java.sql.SQLException;
import java.util.List;

import quizsite.controllers.Util;
import quizsite.models.Friendship;
import quizsite.models.Message;
import quizsite.models.User;
import quizsite.util.Activity;

/**
 * 
 */
public class FriendRequest extends Message {
	
	private String callbackURL;
	
	
	public FriendRequest(User sender, User recipient, String url) throws SQLException {
		this(sender, recipient, url, "Friend request!");
	}
	
	public FriendRequest(User sender, User recipient, String url, String subject) throws SQLException {
		super( sender, recipient );
		updateCallbackURL(url);
		setType(Type.FRIEND_REQUEST);
		setSubject(subject);
	}

	/** Creates a new entry in the Friendship row with status PENDING, and returns the id of saved request
	 * @throws SQLException */
	@Override
	public int save() throws SQLException {
		Friendship nf = new Friendship(sender, recipient, getId());
		nf.save();
		int id = super.save();
		String msg = sender.getName() + " wants to friend you, " + recipient.getName() +". Do you want to " 
			+ Util.wrapURL(acceptURL(nf.getId()), "accept") + " it or " + Util.wrapURL(rejectURL(nf.getId()), "reject") + " it?";
		setBody(msg);
		update();
		nf.setFriendRequestId(id);
		nf.update();
		return id;
	}
	
	public int send() throws SQLException {
		return save();
	}

	/**
	 * @param callbackURL the callbackURL to set
	 */
	public void updateCallbackURL(String callbackURL) {
		if (getSender() != null && getRecipient() != null) {
		this.callbackURL = callbackURL; 
		}
	}


	public static String rejectURL(int id) {
		return "reject_friendship?friendship_id=" + id;
	}

	public static String acceptURL(int id) {
		return "accept_friendship?friendship_id=" + id;
	}

	/**
	 * @return the callbackURL
	 */
	public String getCallbackURL() {
		return callbackURL;
	}

	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		// Not parsing callbackURL
	}

	@Override
	public Activity getActivity() {
		// THIS DOES NOT NEED TO BE IMPLEMENTED
		return null;
	}
	

}
