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
	
	public FriendRequest(User recipient, User sender, String url) throws SQLException {
		super(recipient, sender);
		setCallbackURL(url);
		formatString = "This is a friend request from %1$s to %2$s containing the url %3$s";
		setBody(formatBody(sender, recipient, callbackURL));
		
		setType(Type.FRIEND_REQUEST);
	}

	/**
	 * @param callbackURL the callbackURL to set
	 */
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	/**
	 * @return the callbackURL
	 */
	public String getCallbackURL() {
		return callbackURL;
	}

	@Override
	public void parse(List<String> dbEntry) {
		// TODO Auto-generated method stub
		
	}
}
