package in.rapps.utils;

import in.rapps.config.RappConstants;

import java.sql.Connection;
import java.sql.DriverManager;

// A utility to connect Tomcat server to mysql database server
public class ConnectionUtils {

	public static Connection con;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", 
					RappConstants.databaseUser, RappConstants.databasePass);
		} catch(Exception e) {
			System.out.println("The connection could not be initialized. Please check the connection configuration.");
		}
	}
}