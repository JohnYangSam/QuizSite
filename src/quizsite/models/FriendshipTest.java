package quizsite.models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import quizsite.models.Friendship.Status;
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
	
	@Test
	public void testIndex() throws SQLException {
		Friendship[] f = new Friendship[10]; 
		f[0] = new Friendship(users[0], users[1]);
		f[0].save();
		f[1] = new Friendship(users[0], users[2]);
		f[1].save();
		f[2] = new Friendship(users[1], users[2]);
		f[2].save();
		assertEquals(2, Friendship.indexWhereInitiatorIs(users[0]).size());
		assertEquals(1, Friendship.indexWhereInitiatorIs(users[1]).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2]).size());
		assertEquals(2, Friendship.indexWhereInitiatorIs(users[0], Status.PENDING).size());
		assertEquals(1, Friendship.indexWhereInitiatorIs(users[1], Status.PENDING).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2], Status.PENDING).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[0], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[1], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[0], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[1], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2], Status.REJECTED).size());
		
		assertEquals(0, Friendship.indexWhereResponderIs(users[0]).size());
		assertEquals(1, Friendship.indexWhereResponderIs(users[1]).size());
		assertEquals(2, Friendship.indexWhereResponderIs(users[2]).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[0], Status.PENDING).size());
		assertEquals(1, Friendship.indexWhereResponderIs(users[1], Status.PENDING).size());
		assertEquals(2, Friendship.indexWhereResponderIs(users[2], Status.PENDING).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[0], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[1], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[2], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[0], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[1], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[2], Status.REJECTED).size());
		
		// Accept one friendship, reject another
		Friendship.get(f[0].getId()).accept();	// 0 -> 1
		Friendship.get(f[1].getId()).reject();	// 0 -> 2
		
		
		assertEquals(2, Friendship.indexWhereInitiatorIs(users[0]).size());
		assertEquals(1, Friendship.indexWhereInitiatorIs(users[1]).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2]).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[0], Status.PENDING).size());
		assertEquals(1, Friendship.indexWhereInitiatorIs(users[1], Status.PENDING).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2], Status.PENDING).size());
		assertEquals(1, Friendship.indexWhereInitiatorIs(users[0], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[1], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2], Status.ACCEPTED).size());
		assertEquals(1, Friendship.indexWhereInitiatorIs(users[0], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[1], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereInitiatorIs(users[2], Status.REJECTED).size());
		
		assertEquals(0, Friendship.indexWhereResponderIs(users[0]).size());
		assertEquals(1, Friendship.indexWhereResponderIs(users[1]).size());
		assertEquals(2, Friendship.indexWhereResponderIs(users[2]).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[0], Status.PENDING).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[1], Status.PENDING).size());
		assertEquals(1, Friendship.indexWhereResponderIs(users[2], Status.PENDING).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[0], Status.ACCEPTED).size());
		assertEquals(1, Friendship.indexWhereResponderIs(users[1], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[2], Status.ACCEPTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[0], Status.REJECTED).size());
		assertEquals(0, Friendship.indexWhereResponderIs(users[1], Status.REJECTED).size());
		assertEquals(1, Friendship.indexWhereResponderIs(users[2], Status.REJECTED).size());
	}


}
