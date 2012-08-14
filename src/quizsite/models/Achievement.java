package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quizsite.util.DatabaseConnection;
import quizsite.util.PersistentModel;

public class Achievement extends PersistentModel {

	private User user;
	private Type type;
	
	public static String TABLE_NAME = "Achievement";
	public static String[][] SCHEMA = {{"user_id","INTEGER"}, {"type","TINYTEXT"}};
	public static final int I_USER_ID = PersistentModel.N_PRE_COL,
							I_TYPE = PersistentModel.N_PRE_COL + 1;
	
	public static String[][] FOREIGN_KEYS = {{"user_id", "User", "id"}};
	
	public enum Type {
		AMATEUR_AUTHOR("Amateur Author", "Congrats! You just created a quiz!"),
		PROLIFIC_AUTHOR("Prolific Author", "You've created five quizzes. Way to go, champ!"),
		PRODIGIOUS_AUTHOR("Prodigious Author", "All hail the prodigy! You created ten quizzes!"),
		QUIZ_MACHINE("Quiz Machine", "You just took 10 quizzes. Here's some candy!"),
		GREATEST("I am the Greatest", "You are a beast! You got the highest score on a quiz!"),
		PRACTICE("Practice Makes Perfect", "You took a quiz in practice mode. Meh.");
		private String title;
		private String description;
		Type(String title, String description) {
			this.title = title;
			this.description = description;
		}
		public String getTitle() {
			return title;
		}
		public String getDescription() {
			return description;
		}
		public boolean equals(String type) {
			return getTitle().equals(type);
		}
		public static Type get(String type) {
			for (Type t : Type.values()) {
				if (t.equals(type)) {
					return t;
				}
			}
			throw new IllegalArgumentException("No type with the title : " + type);
		}
	}
	
	public Achievement(User user, Type type) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setUser(user);
		setType(type);
	}

	@Override
	public String toString() {
		return getType().getTitle();
	}
	
	@Override
	public Object[] getFields() {
		return new Object[] {getUser().getId(), getType().getTitle()};
	}

	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry);
		setUser(User.get(Integer.parseInt(dbEntry.get(I_USER_ID))));
		setType(dbEntry.get(I_TYPE));
	}

	private void setType(String type) {
		setType(Type.get(type));
	}

	public static List<Achievement> parseRows(List< List<String> > rows) throws SQLException {
		List<Achievement> ret = new ArrayList<Achievement>();
		for (List<String> row : rows) {
			if (row != null) {
				Achievement curr = new Achievement(null, null);
				curr.parse(row);
				ret.add(curr);
			} else {
				ret.add(null);
			}
		}
		return ret;
	}

	public static Achievement get(int id) throws SQLException {
		List<String> entry = DatabaseConnection.get(TABLE_NAME, id);
		if (entry != null) {
			Achievement curr = new Achievement(null, null);
			curr.parse(entry);
			return curr;
		} else {
			return null;
		}
	}
	
	/** Lists achievements of given user */
	public static List<Achievement> ofUser(User user) throws SQLException {
		String[][] conditions = {{"user_id", "=", "" + user.getId()}};
		return parseRows(DatabaseConnection.indexWhere(TABLE_NAME, conditions));
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}
