package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.lang.*;



import quizsite.models.messages.FriendRequest;
import quizsite.util.Activity;
import quizsite.util.DatabaseConnection;
import quizsite.util.PersistentModel;

public class Friendship extends PersistentModel {
	public static String TABLE_NAME = "Friendship";
	public static String[][] SCHEMA = {{"initiator_id", "INTEGER"}, {"responder_id","INTEGER"}, {"status", "TINYTEXT"}, {"friend_request_id", "INTEGER"}};
	public final static int I_INTIATOR_ID = PersistentModel.N_PRE_COL,
							I_RESPONDER_ID =  PersistentModel.N_PRE_COL + 1,
							I_STATUS =  PersistentModel.N_PRE_COL + 2,
							I_FRIEND_REQUEST_ID =  PersistentModel.N_PRE_COL + 3;
	
	protected static String[][] FOREIGN_KEYS = {{"initiator_id", User.TABLE_NAME, "id"}, {"responder_id", User.TABLE_NAME, "id"}, {"friend_request_id", FriendRequest.TABLE_NAME, "id"}};
	
	private User initiator;
	private User responder;
	private Status status;
	private int friendRequestId;

	public enum Status {
		PENDING, ACCEPTED, REJECTED;
		@Override
		public String toString() {
			return this.name();
		}
		public boolean equals(String stat) {
			return this.toString().equals(stat);
		}
		public static Status get(String stat) {
			for (Status s : Status.values()) {
				if (s.equals(stat)) {
					return s;
				}
			}
			return null;
		}
	}

	public Friendship(User initiator, User responder, int friendRequestId) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setInitiator(initiator);
		setResponder(responder);
		setStatus(Status.PENDING);
		setFriendRequestId(friendRequestId);
	}

	public Friendship(User user, User user2) throws SQLException {
		this(user,user2, -1);
	}
	
	public static Friendship getByFriendRequestId(int friendReqId) throws Exception {
		String[][] conditions = { {"friend_request_id", "=", "" + friendReqId} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		List<Friendship> fl =  parseRows(rows);
		switch (fl.size()) {
		case 0:
			return null;
		case 1:
			return fl.get(0);
		default:
			throw new Exception("ALERT! Each friend request should ahve only one corresponding friendship entry");
		}
	}

	public void setFriendRequestId(int friendRequestId) {
		this.friendRequestId = friendRequestId;
	}

	public int getFriendRequestId() {
		return this.friendRequestId;
	}
	/** Accepts and updates db*/
	public void accept() throws SQLException {
		setStatus(Status.ACCEPTED);
		update();
	}
	
	/** Rejects and updates db */
	public void reject() throws SQLException {
		setStatus(Status.REJECTED);
		update();
	}

	/** Returns all friendship entries initiated by given user , irrespective of status */
	public static List<Friendship> indexWhereInitiatorIs(User user) throws SQLException {
		String[][] conditions = { {"initiator_id", "=", "" + user.getId()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}
	
	/** Returns all friendship entries initiated by given user , corresponding to given status */
	public static List<Friendship> indexWhereInitiatorIs(User user, Status status) throws SQLException {
		String[][] conditions = { {"initiator_id", "=", "" + user.getId()}, {"status", "=", status.toString()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}
	
	/** Returns all friendship entries where user plays the role of responder, irrespective of status */
	public static List<Friendship> indexWhereResponderIs(User user) throws SQLException {
		String[][] conditions = { {"responder_id", "=", "" + user.getId()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}
	
	/** Returns all friendship entries where user plays the role of responder, corresponding to given status */
	public static List<Friendship> indexWhereResponderIs(User user, Status status) throws SQLException {
		String[][] conditions = { {"responder_id", "=", "" + user.getId()}, {"status", "=", status.toString()} };
		List<List<String> > rows = DatabaseConnection.indexWhere(TABLE_NAME, conditions);
		return parseRows(rows);
	}
	
	/** Return all friendships involving user, corresponding to given status 
	 * @throws SQLException */
	public static List<Friendship> indexFor(User user, Status status) throws SQLException {
		List<Friendship> all = indexWhereInitiatorIs(user, status);
		all.addAll(indexWhereResponderIs(user, status));
		return all;
	}
	
	/** Returns a list of all users involved in the list of friendships - each user added only once */
	public static List<User> getUsersFrom(List<Friendship> lf) {
		Set<User> su = new HashSet<User>();
		for (Friendship f : lf) {
			su.add(f.getInitiator());
			su.add(f.getResponder());
		}
		List<User> lu = new ArrayList<User>();
		for (User u : su) {
			lu.add(u);
		}
		return lu;
	}
	
	
	public static List<Friendship> parseRows(List<List<String>> rows) throws SQLException {
		List<Friendship> ret = new ArrayList<Friendship>();
		for (List<String> row : rows) {
			Friendship curr = new Friendship(null, null, -1); 
			curr.parse(row);
			ret.add(curr);
		}
		return ret;
	}
	
	
	public static Friendship get(int id) throws SQLException {
		List<String> entry = DatabaseConnection.get(TABLE_NAME, id);
		if (entry != null) {
			Friendship curr = new Friendship(null, null, -1);
			curr.parse(entry);
			return curr;
		} else {
			return null;
		}
	}


	/** 
	 * Parses a list of strings representing the values in a dbEntry and 
	 * populates the Friendship instance variables with those values in order
	 * @throws SQLException 
	 * @throws IllegalArgumentException 
	 */
	@Override
	public void parse(List<String> dbEntry) throws IllegalArgumentException, SQLException {
		super.parse(dbEntry); //This takes care of the id which is the first attribute for
		setInitiator(User.get(Integer.parseInt(dbEntry.get(I_INTIATOR_ID))));
		setResponder(User.get(Integer.parseInt(dbEntry.get(I_RESPONDER_ID))));
		setStatus(dbEntry.get(I_STATUS));
		setFriendRequestId(Integer.parseInt((dbEntry.get(I_FRIEND_REQUEST_ID))));
	}
	

	@Override
	public Object[] getFields() {
		Object[] objs = new Object[] {getInitiator().getId(), getResponder().getId(), getStatus().toString(), getFriendRequestId()};
		return objs;
	}
	
	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	public User getInitiator() {
		return initiator;
	}

	public void setResponder(User responder) {
		this.responder = responder;
	}

	public User getResponder() {
		return responder;
	}

	public void setStatus(String status) {
		this.status = Status.get(status);
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public Activity getActivity() {
		return new Activity(initiator.getId(), initiator.getName(), getCreatedAt(), status.toString(), responder.getName());
	}


}
