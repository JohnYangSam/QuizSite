package quizsite.models.messages;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.models.User;
import quizsite.util.DatabaseConnection;
import quizsite.util.ForeignKey;

public class NoteTest {

	private Map< String, HashSet<String> > referencedTables;
	private String sampleBody = "This body is for testing purposes";
	
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
		referencedTables = ForeignKey.getDependencies(Note.FOREIGN_KEYS);
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
		DatabaseConnection.dropTablesIfExist( Note.TABLE_NAME );
	}

	@Test
	public void testCreateTableIfNotExists() throws SQLException {
		assertFalse(DatabaseConnection.doesTableExist(Note.TABLE_NAME));
		Note newM = new Note(null, null, sampleBody);
		assertTrue(DatabaseConnection.doesTableExist(Note.TABLE_NAME));
	}
	
	/** Tests saving of row in database */
	@Test
	public void testDBCreate() throws SQLException {
		User sender = new User(1);
		Note newM = new Note(sender, sender, sampleBody);
		int mId = DatabaseConnection.create(newM);
		System.out.println(mId);
	}
	
	/** Similar to testDBCreate, but tests wrapper method in Model */
	@Test
	public void testSave() throws SQLException {
		User sender = new User(1);
		Note newM = new Note(sender, sender, sampleBody);
		int mId = newM.save();
		System.out.println(mId);
	}
	
	@Test
	public void testDBGet() throws SQLException {
		User sender = new User(1);
		FriendRequest newM = new FriendRequest(sender, sender, "example.com");
		int mId = newM.save();
		List<String> out = DatabaseConnection.get(Note.TABLE_NAME, mId);
		System.out.println(out);
		System.out.println(mId);
	}
	
	
	/** Similar to testDBGet but via wrapper in model 
	 * @throws SQLException */
	@Test
	public void testGet() throws SQLException {
		User sender = new User(1);
		Note newM = new Note(sender, sender, sampleBody);
		int mId = newM.save();
		Note newN = Note.get(mId);
		System.out.println(newN.getBody());
		assertEquals(newM.getBody(), newN.getBody());
	}
	
	@After
	public void tearDown() throws Exception {
		DatabaseConnection.dropTablesIfExist( getReferencedTableNames());
		DatabaseConnection.dropTablesIfExist( Note.TABLE_NAME );
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
	}

}