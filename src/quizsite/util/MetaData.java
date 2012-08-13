package quizsite.util;

import java.util.ArrayList;
import java.util.List;

import quizsite.controllers.Util;

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
		String[] schemaStr = new String[schema.length];
		for (int i = 0; i < schema.length; i++) {
			schemaStr[i] = schema[i][0] + " " + schema[i][1]; 
		}
		String ret = Util.join(schemaStr, " , ");
		return ret;
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

	/** 
	 * Returns the number of pre-defined columns specified by {@link PersistentModel#N_PRE_COL}
	 * plus the number of columns specified by the SCHEMA static field in the subclass of 
	 * PersistentModel */
	public int getNumberOfColumns() {
		return getSchema().length + PersistentModel.N_PRE_COL;
	}
}