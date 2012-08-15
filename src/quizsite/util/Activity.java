package quizsite.util;

/**
 * A class representing an activity that has taken place
 */
public class Activity {
	/* Instance variables */
	private String userName;
	private String date;
	private String verb;
	private String subject;
	
	public Activity(String userName, String date, String verb, String subject) {
		this.userName = userName;
		this.date = date;
		this.verb = verb;
		this.subject = subject;
	}
	
	public String getUser() {
		return userName;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getVerb() {
		return verb;
	}
	
	public String subject() {
		return subject;
	}
	
}
