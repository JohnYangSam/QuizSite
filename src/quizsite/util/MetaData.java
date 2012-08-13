package quizsite.util;

import java.util.ArrayList;
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

	
	public String getSchemaStringified() {
		StringBuilder sb = new StringBuilder();
		for (String[] col : schema) {
			sb.append("," + col[0] + " " + col[1]);
		}
		String res = sb.toString();
		return res;
	}
	
	/** Returns the 2D string array of schema information */
	public String[][] getSchema() {
		return schema;
	}
	

	public List<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	/**
	 * @return a list of strings, where each entry is a column name
	 */
	public List<String> getColumnNames() {
		List<String> columnNames = new ArrayList<String>();
		for (String[] col : schema) {
			columnNames.add(col[0]);
		}
		return columnNames;
	}

	public int getNumberOfColumns() {
		return getSchema().length + 1;
	}
}