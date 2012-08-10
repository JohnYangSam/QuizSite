package quizsite.util;

import java.util.List;

public class MetaData {
	private final String tableName ;
	private final String schema ;
	private final List<ForeignKey> foreignKeys;
	
	public MetaData(String tableName, String schema, String[][] foreignKeyStrings) {
		this.tableName = tableName;
		this.schema = schema;
		this.foreignKeys = ForeignKey.getList(foreignKeyStrings);
	}
	
	public String getTableName() {
		return tableName;
	}

	public String getSchema() {
		return schema;
	}

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}
}