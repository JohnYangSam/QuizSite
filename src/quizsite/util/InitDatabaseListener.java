package quizsite.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import quizsite.models.*;
import quizsite.models.messages.*;
import quizsite.models.questions.CheckboxQuestion;

/**
 * Application Lifecycle Listener implementation class InitDatabaseListener
 *
 */
@WebListener
public class InitDatabaseListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitDatabaseListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	//By Instantiating each of these objects we will instantiate empty tables
    	//if the tables do not exist already. We do not save any of the models so
    	//the tables will remain empty
    	try {
    		dropTables();
    		createTables();
    	} catch (Exception e) {
    		System.err.println("QuizSite : Error : While dropping and creating tables");
    		e.printStackTrace();
    	}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
    
    @SuppressWarnings("unused")
	private void createTables() throws SQLException {
    	User user0 				= new User(null, null, null, null);
    	System.out.println("User Init");
    	Friendship friendship0	= new Friendship(null, null);
    	System.out.println("Friendship Init");
    	Message message0		= new Note(null, null, null);
    	System.out.println("Message Init");
    	Quiz quiz0				= new Quiz(false, false, false, false, 0);
    	System.out.println("Quiz Init");
    	Set<String> filler = new HashSet<String>();
    	Question question0		= new CheckboxQuestion(filler, null, 0, new ArrayList<String>());
    	System.out.println("Question Init");
    	Attempt attempt0		= new Attempt(null, null , 0);
    	System.out.println("Attempt Init");
    	Achievement ach0 = new Achievement(null, null);
    }
	
    
    private void dropTables() throws SQLException {
    	DatabaseConnection.dropTablesIfExist(Achievement.TABLE_NAME, Attempt.TABLE_NAME, Question.TABLE_NAME, Quiz.TABLE_NAME, Message.TABLE_NAME, Friendship.TABLE_NAME, User.TABLE_NAME);
    }
}
