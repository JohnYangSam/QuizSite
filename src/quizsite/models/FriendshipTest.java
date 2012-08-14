package quizsite.models;


import org.junit.After;
import org.junit.Before;

import quizsite.util.DatabaseConnection;

public class FriendshipTest {

	private String[] userNames = {"Vighnesh","Bruce","Logan"};
	private String[] emails = {"vig@com", "bru@way", "wol@x"};
	
	private String[] passwordSaltedHashes = {"asde","rgerhg5","fefsef"};
	private String[] passwordSalts = {"3r23w","23rf24f","23r23"};
	
	private User[] users;
	private int[] ids;
	
	@Before
	public void setUp() throws Exception {
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.TEST);
		DatabaseConnection.dropTablesIfExist( User.TABLE_NAME );
		DatabaseConnection.dropTablesIfExist( Friendship.TABLE_NAME );
		// Save some users
		users = new User[3];
		ids = new int[3];
		for (int i = 0; i < users.length; i++) {
			users[i] = new User(userNames[i], emails[i], passwordSaltedHashes[i], passwordSalts[i]);
			ids[i] = users[i].save();
		}
	}

	@After
	public void tearDown() throws Exception {
		DatabaseConnection.dropTablesIfExist( User.TABLE_NAME );
		DatabaseConnection.dropTablesIfExist( Friendship.TABLE_NAME );
		DatabaseConnection.switchModeTo(DatabaseConnection.Mode.PRODUCTION);
	}
	
	


}
