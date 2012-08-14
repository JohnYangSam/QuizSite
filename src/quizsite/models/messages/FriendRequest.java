/**
 * File: FriendRequest.java
 * Created: Aug 6, 2012
 */
package quizsite.models.messages;

import java.sql.SQLException;
import java.util.List;

import quizsite.models.Message;
import quizsite.models.User;

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
	

}
