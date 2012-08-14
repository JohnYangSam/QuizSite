package quizsite.models;

import java.sql.SQLException;
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
		PENDING("PENDING"),
		ACCEPTED("ACCEPTED"),
		REJECTED("REJECTED");
		private String repr;
		Status(String repr) {
			setRepr(repr);
		}
		public void setRepr(String repr) {
			this.repr = repr;
		}
		@Override
		public String toString() {
			return repr;
		}
		public static Status get(String status) {
			for (Status s : Status.values()) {
				if (status.equals(s)) {
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
	
	
	public static List<Friendship> indexWhereInitiatorIs(User user) {
		return null;
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
