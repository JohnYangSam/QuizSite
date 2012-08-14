package quizsite.controllers;

import java.io.IOException;
import java.security.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import quizsite.models.User;


/**
 * Servlet implementation class RegisterUserController
 */
@WebServlet("/RegisterUserController")
public class RegisterNewUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterNewUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get take in the userName and password
		//Check if the user exists in the database yet
			//Redirect them otherwise ----->>
		//Salt the password and hash
		//Create a user in the database
		//Set the servlet context
		// send the user to the homepage ----->>
			
		//Get parameters from the request
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		
		
		//TODO: NOTE for RegisterUserView.jsp
		//if (request.getAttribute("failuireMessage") == NULL don't print out anything, otherwise print out the message
		
		//Check for empty case
		if(userName == "") {
			request.setAttribute("failureMessage", "Can not leave user name blank. Please enter a valid user name");
			RequestDispatcher dispatch = request.getRequestDispatcher(Util.REGISTER_NEW_USER_VIEW);
			dispatch.forward(request, response);
			return;
		} else {
	
		//Catch SQLExceptions
		try {
			
			//Existing account case
			if (User.userExists(userName)) {
				request.setAttribute("failureMessage", "User name is already taken. Please try another user name.");
				RequestDispatcher dispatch = request.getRequestDispatcher(Util.REGISTER_NEW_USER_VIEW);
				dispatch.forward(request, response);
				return;
		
			//Password not the same
			} else if (!password.equals(passwordConfirm)) {
				request.setAttribute("failureMessage", "Password and password confirmation do not match. Please try registering again");
				RequestDispatcher dispatch = request.getRequestDispatcher(Util.REGISTER_NEW_USER_VIEW));
				dispatch.forward(request, response);
				return;
			
			//Salt, has, and a new account to accounts
			} else {
				//Add user and then set the USER_SESSION_KEY to the userId
				int userId = registerNewUser(userName, password);
				HttpSession session = request.getSession();
				session.setAttribute(Util.USER_SESSION_KEY, userId);
				//Send to the main view
				RequestDispatcher dispatch = request.getRequestDispatcher(Util.MAIN_VIEW);
				dispatch.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("SQL Error while autheticating user registration: ");
			e.printStackTrace();
		}	
	}
	
	

	
/* ------------------------- Hashing and salting methods -------------------------*/	
	
	/**
	 * Returns a secure random salt string to be added to the password before hashing.
	 */
	
	private static String generateSalt() {
		String saltStr = "";
		Random r = new SecureRandom();
		byte[] salt = new byte[20];
		r.nextBytes(salt);
		saltStr = new String(salt);
		
		return saltStr;
	}
	
	
	/**
	 * Takes in a string input and a salt string to be hashed and returns an equivalent salted hash based
	 * on the SHA hash algorithm.
	 */
	private static String makeSaltedHash(String password, String salt) {
		String result = "";
		
		//Convert password to bytes and concatenate with the salt
		byte[] passwordAndSaltBytes = concatByteArrays(password.getBytes(), salt.getBytes());
		
		
		try {
			//Compute password hash
			MessageDigest messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update(passwordAndSaltBytes);
			byte[] passwordAndSaltDigestBytes = messageDigest.digest();
			result = hexToString(passwordAndSaltDigestBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static byte[] concatByteArrays(byte[] arr1, byte[] arr2) {
		byte[] result = new byte[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, result, 0, arr1.length);
		System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
		
		return result;
	}
	
	
	/**
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}
	
	/**
	 Given a string of hex byte values such as "24a26f", creates
	 a byte[] array of those values, one byte value -128..127
	 for each 2 chars.
	 (provided code)
	*/
	public static byte[] hexToArray(String hex) {
		byte[] result = new byte[hex.length()/2];
		for (int i=0; i<hex.length(); i+=2) {
			result[i/2] = (byte) Integer.parseInt(hex.substring(i, i+2), 16);
		}
		return result;
	}

}
