/**
 * File: FriendRequest.java
 * Created: Aug 6, 2012
 */
package quizsite.models.messages;

import java.sql.SQLException;
import java.util.List;

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
		super( sender, recipient );
		updateCallbackURL(url);
		setType(Type.FRIEND_REQUEST);
	}

	/** Creates a new entry in the Friendship row with status PENDING, and returns the id of the friendship entry 
	 * @throws SQLException */
	public int send() throws SQLException {
		save();
		Friendship nf = new Friendship(sender, recipient);
		return nf.save();
	}

	/**
	 * @param callbackURL the callbackURL to set
	 */
	public void updateCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
		formatString = "This is a friend request from %1$s to %2$s containing the url %3$s";
		if (sender != null && recipient != null) {
			setBody(formatBody(sender.getId(), recipient.getId(), callbackURL));
		}
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
