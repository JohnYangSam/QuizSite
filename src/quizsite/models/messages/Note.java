package quizsite.models.messages;

import java.sql.SQLException;
import java.util.List;

import quizsite.models.Message;
import quizsite.models.User;
import quizsite.util.DatabaseConnection;

public class Note extends Message {
	private String content;
	
	public Note(User sender, User recipient, String content) throws SQLException {
		super( sender, recipient );
		updateContent(content);
		setType(Type.NOTE);
	}


	/**
	 * @param content the content to set
	 */
	public void updateContent(String content) {
		this.content = content;
		setBody(content);
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/** 
	 * Gets a row from Message table and parses it assuming it is a note 
	 * DEPRECATED! Prefer Message.get()
	 * */
	public static Note get(int id) throws SQLException {
		Note newN = new Note(null, null, "");
		List<String> entry = DatabaseConnection.get(newN.getTableName(), id);
		if (entry != null) {
			newN.parse(entry);
			return newN;
		} else {
			return null;
		}
	}

	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException{
		super.parse(dbEntry);
		// All columns parsed - no need to parse content. Just set it equal to body
		updateContent(getBody());
	}


}
