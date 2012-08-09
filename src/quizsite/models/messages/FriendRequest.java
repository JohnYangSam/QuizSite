/**
 * File: FriendRequest.java
 * Created: Aug 6, 2012
 */
package quizsite.models.messages;

import quizsite.models.Message;
import quizsite.models.User;

/**
 * 
 */
public class FriendRequest extends Message {
	
	private String callbackURL;
	
	public FriendRequest(User recipient, User sender, String url) {
		super(recipient, sender);
		setCallbackURL(url);
		formatString = "This is a friend request from %1$s to %2$s containing the url %3$s";
		setBody(sender, recipient, callbackURL);
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
}
