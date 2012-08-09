package quizsite.models.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import quizsite.models.Message;
import quizsite.util.DatabaseConnection;
import quizsite.util.ForeignKey;

public class MessageTest {

	private String getCreateSqlStatement(Map.Entry<String, HashSet<String> > pairs) {
		String result = "CREATE TABLE IF NOT EXISTS " + pairs.getKey() + "( " ;
		for (String hs : pairs.getValue()) {
			result += hs + ",";
		}
		result = result.substring(0, result.length() - 1);
		result += ")";
		return result;
	} 
	
	private void createReferencedTables() throws SQLException {
		Map< String, HashSet<String> > dep = ForeignKey.getDependencies(Message.getForeignKeys());
	    Iterator< Map.Entry<String, HashSet<String> > > it = dep.entrySet().iterator();
		DatabaseConnection db = new DatabaseConnection();
	    
	    while (it.hasNext()) {
			Map.Entry<String, HashSet<String> > pairs = (Map.Entry<String, HashSet<String> >) it.next();
			String sql = getCreateSqlStatement(pairs);
			db.executeUpdate(sql);
		}
	    db.close();
	}
	
	
	
	@Before
	public void setUp() throws Exception {
		createReferencedTables();
	}

	@Test
	public void testCreateTableIfNotExists() throws SQLException {
		DatabaseConnection db = new DatabaseConnection();
		String sql = Message.createTableQuery();
		db.executeUpdate(sql);
		db.close();
	}

}
