package quizsite.models;

import java.sql.SQLException;

import quizsite.util.Activity;
import quizsite.util.PersistentModel;

public class Announcement extends PersistentModel {

	public Announcement(String tableName, String[][] schema,
			String[][] foreignKeys) throws SQLException {
		super(tableName, schema, foreignKeys);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return null;
	}

}
