package quizsite.util;

import java.util.*;

import quizsite.controllers.Util;

public class ForeignKey {

	private String columnName;
	private String referencedTableName;
	private String referencedColumnName;
	
	public ForeignKey(String columnName, String referencedTableName, String referencedColumnName) {
		setColumnName(columnName);
		setReferencedTableName(referencedTableName);
		setReferencedColumnName(referencedColumnName);
	}

	@Override
	public String toString() {
		String cmd = " FOREIGN KEY ( " + columnName + " ) REFERENCES " + referencedTableName + 
					  " ( " + referencedColumnName + " ) ";
		return cmd;
	}
	
	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param referencedTableName the referencedTableName to set
	 */
	public void setReferencedTableName(String referencedTableName) {
		this.referencedTableName = referencedTableName;
	}

	/**
	 * @return the referencedTableName
	 */
	public String getReferencedTableName() {
		return referencedTableName;
	}

	/**
	 * @param referencedColumnName the referencedColumnName to set
	 */
	public void setReferencedColumnName(String referencedColumnName) {
		this.referencedColumnName = referencedColumnName;
	}

	/**
	 * @return the referencedColumnName
	 */
	public String getReferencedColumnName() {
		return referencedColumnName;
	}

	/**
	 * eg. ", FOREIGN KEY( column_name ) REFERENCES table_name (col_name)"*
	 * */
	public static String serialize(List<ForeignKey> foreignKeys) {
		return Util.join(foreignKeys, " , ");
	}
	
	public static HashMap< String, HashSet<String> > getDependencies(List<ForeignKey> fkeys) {
		HashMap< String, HashSet<String> > dep = new HashMap<String, HashSet<String>>();
		HashSet<String> s;
		for (ForeignKey fkey : fkeys) {
			if (dep.containsKey(fkey.getReferencedTableName())) {
				s = dep.get(fkey.getReferencedTableName());
				s.add(fkey.getReferencedColumnName());
			} else {
				s = new HashSet<String>();
				s.add(fkey.getReferencedColumnName());
				dep.put(fkey.getReferencedTableName(), s);
			}
		}
		return dep;
	}
	
	public static HashMap< String, HashSet<String> > getDependencies(String[][] fkeys) {
		return getDependencies(getList(fkeys));
	}

	// Returns an array 
	public static List<ForeignKey> getList(String[][] foreignKeyStrings) {
		ArrayList<ForeignKey> arr = new ArrayList<ForeignKey>();
		for (String[] fKey : foreignKeyStrings) {
			arr.add(new ForeignKey(fKey[0], fKey[1], fKey[2]));
		}
		return arr;
	}
}
