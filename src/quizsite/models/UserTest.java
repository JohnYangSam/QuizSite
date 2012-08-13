package quizsite.models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import quizsite.util.DatabaseConnection;

public class UserTest {
	private String[] userNames = {"Vighnesh","Bruce","Logan"};
	private String[] emails = {"vig@com", "bru@way", "wol@x"};
	private String[] passwordSaltedHashes = {"asde","rgerhg5","fefsef"};
	private String[] passwordSalts = {"3r23w","23rf24f","23r23"};
	
	private User[] users;

	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		DatabaseConnection.dropTablesIfExist( User.TABLE_NAME );
		users = new User[3];
		int i = 0;
		int[] ids = new int[3];
		for (User u : users) {
			u = new User(userNames[i], emails[i], passwordSaltedHashes[i], passwordSalts[i]);
			ids[i] = u.save();
			users[i] = u;
			i++;
		}
	}

	@After
	public void tearDown() throws Exception {
		DatabaseConnection.dropTablesIfExist(User.TABLE_NAME);
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
	}

	@Test
	public void testIndex() throws SQLException {
		List<User> all = User.index();
		int i = 0;
		for (User u : all) {
			assertTrue(userNames[i].equals(u.getUserName()));
			i++;
		}
	}

	@Test
	public void testGet() throws SQLException {
		assertTrue(User.get(1).getUserName().equals(userNames[0]));
		assertTrue(User.get(3).getUserName().equals(userNames[1]));
		assertTrue(User.get(5).getUserName().equals(userNames[2]));
		assertNull(User.get(100));
	}

	@Test
	public void testSave() {
		// Tested in index
	}

	@Test
	public void testDelete() throws SQLException {
		for (User u: users) {
			u.delete();
		}
		List<User> all = User.index();
		assertEquals(0, all.size());
	}

}
