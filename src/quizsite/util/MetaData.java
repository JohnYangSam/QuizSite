package quizsite.util;

import java.util.List;

public class MetaData {
	private final String tableName ;
	private final String[][] schema ;
	private final List<ForeignKey> foreignKeys;

	public MetaData(String tableName, String[][] schema, String[][] foreignKeyStrings) {
		this.tableName = tableName;
		this.schema = schema;
		this.foreignKeys = ForeignKey.getList(foreignKeyStrings);
	}

	public String getTableName() {
		return tableName;
	}

	public String getSchema() {
//		if (schema.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String[] col : schema) {
				sb.append("," + col[0] + " " + col[1]);
			}
			String res = sb.toString();
			return res;//.substring(0, res.length() - 1);
//		} else {
//			return "";
//		}
	}

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public String getColumnNames() {
		if (schema.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (String[] col : schema) {
				sb.append(col[0] + ",");
			}
			String res = sb.toString();
			return res.substring(0, res.length() - 1);
		} else {
			return "";
		}
	}
}