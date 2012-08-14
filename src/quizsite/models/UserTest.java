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
	private String[] updEmails = {"hacked", "booyagh", "mwahaha"};
	
	private String[] passwordSaltedHashes = {"asde","rgerhg5","fefsef"};
	private String[] passwordSalts = {"3r23w","23rf24f","23r23"};
	
	private User[] users;
	private int[] ids;

	/** Saves some test users */
	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		DatabaseConnection.dropTablesIfExist( User.TABLE_NAME );
		users = new User[3];
		ids = new int[3];
		for (int i = 0; i < users.length; i++) {
			users[i] = new User(userNames[i], emails[i], passwordSaltedHashes[i], passwordSalts[i]);
			ids[i] = users[i].save();
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
	public void testGetAnGetdByUserName() throws SQLException {
		assertTrue(User.get(1).getUserName().equals(userNames[0]));
		assertTrue(User.get(3).getUserName().equals(userNames[1]));
		assertTrue(User.get(5).getUserName().equals(userNames[2]));
		assertNull(User.get(100));
		
		assertTrue(User.getUserByName("Vighnesh").equals(User.get(1)));
		assertTrue(User.get(3).getUserName().equals(userNames[1]));
		assertNull(User.getUserByName("granny"));
		assertNull(User.getUserByName(""));
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
	
	@Test
	public void testUpdate() throws SQLException {
		for (int i = 0; i < users.length; i++) {
			users[i].setEmail(updEmails[i]);
			users[i].update();
			assertEquals(updEmails[i], User.get(users[i].getId()).getEmail());
		}
	}

	@Test
	public void testLists() throws SQLException {
		Friendship f = new Friendship(users[0], users[1]);
		f.save();
		f = new Friendship(users[0], users[2]);
		f.save();
		f = new Friendship(users[1], users[2]);
		f.save();
		List<User> list = users[0].sentFriendRequestsTo();
		assertEquals(2, list.size());
		assertEquals(1, users[1].sentFriendRequestsTo().size());
		assertEquals(0, users[2].sentFriendRequestsTo().size());
		
		assertEquals(0, users[0].gotFriendRequestsFrom().size());
		assertEquals(1, users[1].gotFriendRequestsFrom().size());
		assertEquals(2, users[2].gotFriendRequestsFrom().size());
	}
	
	@Test
	public void testExists() throws SQLException {
		assertEquals(User.userExists("Vighnesh"), true);
		assertEquals(User.userExists("MMAILK"), false);
		assertEquals(User.userExists("Bruce"), true);
		assertEquals(User.userExists("Logan"), true);
		assertEquals(User.userExists(""), false);
	}
}
