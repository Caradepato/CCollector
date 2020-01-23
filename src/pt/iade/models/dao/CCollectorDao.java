package pt.iade.models.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pt.iade.cCollector.models.MysqlConnection;

public class CCollectorDao {
	
	private static java.sql.Connection connection;
	
	
	/** VerifyPassword controls whether the entered password matches what is registered in the database.
	 * 	It takes the entered email and password and returns a boolean : True if it matches and False if it either does not match or if the email is not registered **/
	
	public static boolean verifyPassword(String email, String password) {
		
		String query = "SELECT User.password FROM User WHERE User.userEmail LIKE ?;";
		String pass = "";
		try {
			connection = MysqlConnection.getConnection();
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1,email);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				pass = rs.getString("password");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		
		return pass.equals(password) && pass != "";
		
		
	}
		
	/** Registers a new user on the database **/
	
	public static void registerNewUser(String email, String password) {
		connection = MysqlConnection.getConnection();
		String query = "Insert into User (userEmail, password) Values (?,?);";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/** checks if a user is already registered **/
	
	public static boolean userExists(String userEmail) {
		connection = MysqlConnection.getConnection();
		String query = "SELECT User.userEmail FROM User WHERE User.userEmail LIKE ?";
		try {
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, userEmail);
			ResultSet rs = st.executeQuery();
			return rs.next();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
