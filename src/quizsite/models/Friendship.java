package quizsite.models;

import java.sql.SQLException;

import quizsite.util.PersistentModel;

public class Friendship extends PersistentModel {
	public static String TABLE_NAME = "Friendship";
	public static String[][] SCHEMA = {{"uid1", "INTEGER"}, {"uid2","INTEGER"}, {"created_at", "DATETIME"}};
	public final static int I_UID1 = 1, I_UID2 = 2;
	protected static String[][] FOREIGN_KEYS = {};
	
	public Friendship(String tableName, String[][] schema,
			String[][] foreignKeys) throws SQLException {
		super(tableName, schema, foreignKeys);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object[] getFields() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
/*
 * 	
 * instance varialbes
	Parse
	
*/