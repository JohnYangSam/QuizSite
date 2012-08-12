/**
 * File: DatabaseConnection.java
 * Created: Aug 11, 2012
 */
package quizsite.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */
public class DatabaseConnectionTest {

	static final String TEST_TABLE = "test_table";
	DatabaseConnection dbInstance;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		dbInstance = new DatabaseConnection();
		DatabaseConnection.dropTablesIfExist(TEST_TABLE);
		DatabaseConnection.createTableIfNotExists(new Something(5, "something"));
		dbInstance.executeUpdate("SET @@auto_increment_increment=1;");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
		DatabaseConnection.dropTablesIfExist(TEST_TABLE);
		dbInstance.close();
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#parseResultData(java.sql.ResultSet)}.
	 */
	@Test
	public void testParseResultData() {
		try {
			// empty table
			List< List<String> > rs = DatabaseConnection.parseResultData(dbInstance.executeQuery("select * from test_table;"));
			assertEquals(0, rs.size());
			
			// one entry, two columns
			dbInstance.executeUpdate("insert into test_table (data) VALUES('fasdf');");
			rs = DatabaseConnection.parseResultData(dbInstance.executeQuery("select * from test_table;"));
			assertEquals(1, rs.size());
			assertEquals(3, rs.get(0).size());
			
			// two entries, two columns
			dbInstance.executeUpdate("insert into test_table (data) VALUES('fasdf');");
			rs = DatabaseConnection.parseResultData(dbInstance.executeQuery("select * from test_table;"));
			assertEquals(2, rs.size());
			for (int i=0; i<rs.size(); i++)
			{ assertEquals(3, rs.get(i).size()); }
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#getGeneratedKey()}.
	 */
	@Test
	public void testGetGeneratedKey() {
		try {
			dbInstance.executeUpdate("insert into test_table (data) VALUES('fasdf');");
			assertEquals(1, dbInstance.getGeneratedKey());
			dbInstance.executeUpdate("insert into test_table (data) VALUES('fasdf');");
			dbInstance.executeUpdate("insert into test_table (data) VALUES('fasdf');");
			assertEquals(3, dbInstance.getGeneratedKey());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#DatabaseConnection()}.
	 */
	@SuppressWarnings("unused")
	@Test
	public void testDatabaseConnection() {
		try {
			DatabaseConnection db = new DatabaseConnection();
		} catch (Exception e) {
			fail("Exception was thrown!");
		}
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#fetchRowById(java.lang.String, int)}.
	 */
	@Test
	public void testFetchRowById() {
		try {
			assertTrue(dbInstance.fetchRowById(TEST_TABLE, 1) == null); // no such index
			
			dbInstance.executeUpdate("insert into test_table (data) VALUES('a');");
			dbInstance.executeUpdate("insert into test_table (data) VALUES('b');");
			dbInstance.executeUpdate("insert into test_table (data) VALUES('c');");
			
			assertTrue(Integer.parseInt(dbInstance.fetchRowById(TEST_TABLE, 1).get(0)) == 1);
			assertTrue(dbInstance.fetchRowById(TEST_TABLE, 1).get(2).equals("a"));
			assertTrue(dbInstance.fetchRowById(TEST_TABLE, 3).get(2).equals("c"));
			assertTrue(dbInstance.fetchRowById(TEST_TABLE, 2).get(2).equals("b"));
			
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#create(quizsite.util.PersistentModel)}.
	 */
	@Test
	public void testCreate() {
		try {
			Something sm = new Something(5, "something 1");
			int lastIndx = sm.save();
			//int lastIndx = DatabaseConnection.create(sm); also works
			assertEquals(1, lastIndx);
			List<String> newSmth = dbInstance.fetchRowById(TEST_TABLE, lastIndx);
			assertEquals(5, Integer.parseInt(newSmth.get(1)));
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#destroy(quizsite.util.PersistentModel)}.
	 */
	@Test
	public void testDestroy() {
		try {
			Something sm = new Something(5, "something 1");
			int id = sm.save();
			DatabaseConnection.destroy(sm);
			List<String> nonExisting = DatabaseConnection.get(TEST_TABLE, id);
			assertNull(nonExisting);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for {@link quizsite.util.DatabaseConnection#update(quizsite.util.PersistentModel)}.
	 */
	@Test
	public void testUpdate() {
		Something sm;
		try {
			sm = new Something(5, "something 1");
			int id = sm.save();
			List<String> newSmth = dbInstance.fetchRowById(TEST_TABLE, id);
			assertTrue(newSmth.get(2).equals("something 1"));
			
			sm.setData("new something");
			sm.setInt(1);
			DatabaseConnection.update(sm);
			
			newSmth = dbInstance.fetchRowById(TEST_TABLE, id);
			assertTrue(newSmth.get(2).equals("new something"));
			assertEquals(1, Integer.parseInt(newSmth.get(1)));
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	
}

class Something extends PersistentModel
{
	private static final String TABLE_NAME = DatabaseConnectionTest.TEST_TABLE;
	private static final String[][] SCHEMA = {{"some_int", "INTEGER"}, {"data", "TEXT"}};
	private static final String[][] FOREIGN_KEYS = {};
	
	private int some_int;
	private String data;
	
	public Something(int si, String data) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		this.some_int = si;
		this.data	  = data;
	}
	
	public void setInt(int newInt)
	{ some_int = newInt; }
	
	public void setData(String newData)
	{ data = newData; }


	@Override
	public Object[] getFields() {
		Object[] objs = new Object[] {some_int, data};
		return objs;
	}

	
}
