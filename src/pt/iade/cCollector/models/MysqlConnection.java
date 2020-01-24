package pt.iade.cCollector.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** handles the connection to the database **/

public class MysqlConnection {
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/Gtwkt5USSe?useSSL=false";
	private static final String USER = "Gtwkt5USSe";
	private static final String PASS = "OxAMLiPad8";
	/*private static final String URL = "jdbc:mysql://remotemysql.com:3306/eBUrTlCHMB?useSSL=false";
	private static final String USER = "eBUrTlCHMB";
	private static final String PASS = "GQkutlzVyF";*/
	
	private static Connection connection = null;
	
	private MysqlConnection () {
	}
	
	/** getConnection gets the connection **/
	
	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) 
				connection = DriverManager.getConnection(URL,USER,PASS);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
