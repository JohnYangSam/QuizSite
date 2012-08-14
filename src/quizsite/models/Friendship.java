package quizsite.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import quizsite.util.DatabaseConnection;
import quizsite.util.PersistentModel;

public class Friendship extends PersistentModel {
	public static String TABLE_NAME = "Friendship";
	public static String[][] SCHEMA = {{"initiator_id", "INTEGER"}, {"responder_id","INTEGER"}, {"status", "TINYTEXT"}};
	public final static int I_INTIATOR_ID = PersistentModel.N_PRE_COL,
							I_RESPONDER_ID =  PersistentModel.N_PRE_COL + 1;
	protected static String[][] FOREIGN_KEYS = {{"initiator_id", User.TABLE_NAME, "id"}, {"responder_id", User.TABLE_NAME, "id"}};
	
	private User initiator;
	private User responder;
	private Status status;
	
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
	
	public Friendship(User initiator, User responder) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setInitiator(initiator);
		setResponder(responder);
		setStatus(Status.PENDING);
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
	
	
	public static List<Friendship> parseRows(List<List<String>> rows) throws SQLException {
		List<Friendship> ret = new ArrayList<Friendship>();
		for (List<String> row : rows) {
			Friendship curr = new Friendship(null, null); 
			curr.parse(row);
			ret.add(curr);
		}
		return ret;
	}
	
	
	public static Friendship get(int id) throws SQLException {
		List<String> entry = DatabaseConnection.get(TABLE_NAME, id);
		if (entry != null) {
			Friendship curr = new Friendship(null, null);
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
		setStatus(status);
	}
	

	@Override
	public Object[] getFields() {
		Object[] objs = new Object[] {getInitiator().getId(), getResponder().getId(), getStatus()};
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


}
