package quizsite.models;

import java.sql.SQLException;

import quizsite.util.PersistentModel;

public class Friendship extends PersistentModel {
	public static String TABLE_NAME = "Friendship";
	public static String[][] SCHEMA = {{"intiator_id", "INTEGER"}, {"acceptor_id","INTEGER"}, {"status", "TINYTEXT"}};
	public final static int I_INTIATOR_ID = PersistentModel.N_PRE_COL,
							I_ACCEPTOR_ID =  PersistentModel.N_PRE_COL + 1;
	protected static String[][] FOREIGN_KEYS = {{"initiator_id", User.TABLE_NAME, "id"}, {"acceptor_id", User.TABLE_NAME, "id"}};
	
	private User initiator;
	private User acceptor;
	
	/** Ugly, but necessary. TODO: please suggest simpler ways */
	public static String getAcceptorIdGivenInitiatorIdQuery(int initiatorId) {
		return "SELECT acceptor_id FROM "+ TABLE_NAME + " WHERE initiator_id = '" + initiatorId + "'";
	}
	
	public Friendship(User initiator, User acceptor) throws SQLException {
		super(TABLE_NAME, SCHEMA, FOREIGN_KEYS);
		setInitiator(initiator);
		setAcceptor(acceptor);
	}

	@Override
	public Object[] getFields() {
		Object[] objs = new Object[] {getInitiator().getId(), getAcceptor().getId()};
		return objs;
	}
	

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	public User getInitiator() {
		return initiator;
	}

	public void setAcceptor(User acceptor) {
		this.acceptor = acceptor;
	}

	public User getAcceptor() {
		return acceptor;
	}

}
