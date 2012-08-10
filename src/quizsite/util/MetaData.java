package quizsite.util;

import java.util.ArrayList;

public class MetaData {
	private final String tableName ;
	private final String schema ;
	private final ArrayList<ForeignKey> foreignKeys;
	
	public MetaData(String tableName, String schema, String[][] foreignKeyStrings) {
		this.tableName = tableName;
		this.schema = schema;
		ArrayList<ForeignKey> arr = new ArrayList<ForeignKey>();
		for (String[] fKey : foreignKeyStrings) {
			arr.add(new ForeignKey(fKey[0], fKey[1], fKey[2]));
		}
		this.foreignKeys = arr;
	}
	
	public String getTableName() {
		return tableName;
	}

	public String getSchema() {
		return schema;
	}

	public ArrayList<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}
}