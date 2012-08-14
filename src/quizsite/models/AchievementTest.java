package quizsite.models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.models.Achievement.Type;
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
	public void testGet() throws SQLException {
		Achievement ach = new Achievement(users[0], Type.AMATEUR_AUTHOR);
		int aId = ach.save();
		assertEquals(Type.AMATEUR_AUTHOR, Achievement.get(aId).getType());
	}

	@Test
	public void testSave() {
		// Tested in get
	}

	@Test
	public void testDelete() throws SQLException {
		Achievement ach = new Achievement(users[0], Type.AMATEUR_AUTHOR);
		int aId = ach.save();
		ach.delete();
		assertNull(Achievement.get(aId));
	}

	@Test
	public void testUpdate() throws SQLException {
		Achievement ach = new Achievement(users[0], Type.AMATEUR_AUTHOR);
		int aId = ach.save();
		ach.setType(Type.GREATEST);
		ach.update();
		assertEquals(Type.GREATEST, Achievement.get(aId).getType());
	}

}
