package quizsite.models;

public class Note extends Message {
	private String content;
	
	public Note(User recipient, User sender, String content) {
		super(recipient, sender);
		setContent(content);
		formatString = content;
		setBody();
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

}
