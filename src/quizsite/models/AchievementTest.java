package quizsite.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.util.DatabaseConnection;

public class AchievementTest {
	private String[] userNames = {"Vighnesh","Bruce","Logan"};
	private String[] emails = {"vig@com", "bru@way", "wol@x"};
	
	private String[] passwordSaltedHashes = {"asde","rgerhg5","fefsef"};
	private String[] passwordSalts = {"3r23w","23rf24f","23r23"};
	
	private User[] users;
	private int[] ids;

	/** Saves some test users */
	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		DatabaseConnection.dropTablesIfExist( User.TABLE_NAME );
		DatabaseConnection.dropTablesIfExist( Achievement.TABLE_NAME );
		users = new User[3];
		ids = new int[3];
		for (int i = 0; i < users.length; i++) {
			users[i] = new User(userNames[i], emails[i], passwordSaltedHashes[i], passwordSalts[i]);
			ids[i] = users[i].save();
		}		
	}

	@After
	public void tearDown() throws Exception {
		DatabaseConnection.dropTablesIfExist( Achievement.TABLE_NAME );
		DatabaseConnection.dropTablesIfExist( User.TABLE_NAME );
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
