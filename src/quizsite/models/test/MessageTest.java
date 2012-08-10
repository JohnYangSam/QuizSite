package quizsite.models.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.models.Message;
import quizsite.util.DatabaseConnection;
import quizsite.util.ForeignKey;

public class MessageTest {

	private Map< String, HashSet<String> > referencedTables;
	
	private String getCreateSqlStatement(Map.Entry<String, HashSet<String> > pairs) {
		String result = "CREATE TABLE IF NOT EXISTS " + pairs.getKey() + "( " ;
		for (String hs : pairs.getValue()) {
			result += hs + " INTEGER,";
		}
		result = result.substring(0, result.length() - 1);
		result += ")";
		return result;
	}
	
	
	private void createReferencedTables() throws SQLException {
		referencedTables = ForeignKey.getDependencies(Message.FOREIGN_KEYS);
	    Iterator< Map.Entry<String, HashSet<String> > > it = referencedTables.entrySet().iterator();
		DatabaseConnection db = new DatabaseConnection();
	    while (it.hasNext()) {
			Map.Entry<String, HashSet<String> > pairs = (Map.Entry<String, HashSet<String> >) it.next();
			String sql = getCreateSqlStatement(pairs);
			db.executeUpdate(sql);
		}
	    db.close();
	}


	private String[] getReferencedTableNames() {
		return referencedTables.keySet().toArray(new String[0]);
	}
	
	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		createReferencedTables();
		DatabaseConnection.dropTablesIfExist( Message.TABLE_NAME );
	}

	@Test
	public void testCreateTableIfNotExists() throws SQLException {
		assertFalse(DatabaseConnection.doesTableExist(Message.TABLE_NAME));
		Message newM = new Message(null, null);
		assertTrue(DatabaseConnection.doesTableExist(Message.TABLE_NAME));
	}
	
	@After
	public void tearDown() throws Exception {
		DatabaseConnection.dropTablesIfExist( getReferencedTableNames());
		DatabaseConnection.dropTablesIfExist( Message.TABLE_NAME );
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
	}

}
