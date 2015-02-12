package in.rapps.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.rapps.models.User;

public class UserService {
	
	static Connection con;
	
	static {
		con = ConnectionUtils.con;
	}
	
	public static User getUserByEmail(String userName) {
		
		if(con == null) {
			return null;
		}
		
		String sql = "SELECT User_name, Password, Name "
				+ "FROM user "
				+ "WHERE User_name='" + userName + "' Limit 1;";

		User user = new User();
		
		Statement pst = null;
		
		try {
			pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);

			while(rs.next()){
				user.setEmail((String) rs.getObject(1));
				user.setPassword((String) rs.getObject(2));
				user.setFullName((String) rs.getObject(3));
			}
		}
		catch(Exception e) {
			System.out.println("Exception in Fetching User.");
		}
		finally {
			if(pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}
		
		return user;
		
	}
	
	public static boolean registerUser(String userName, String password, String fullName) {
		
		if(con == null) {
			return false;
		}
		
		PreparedStatement pst = null;
		int x = 0;
		
		try {
			String sql = "insert into user (User_name, Password, Name) values('" + userName + "','" + password + "','" + fullName + "')";
			pst = con.prepareStatement(sql);
			x = pst.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("exception in register user: " + e.getMessage());
			return false;
		} 
		finally {
			if(pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}

		return (x > 0);
		
	}
	
	public static List<User> getNonFriendUserList(String userName) {
		
		if(con == null) {
			return null;
		}
		
		List<User> nonFriendUserList = new ArrayList<User>();
		
		PreparedStatement pst = null;
		
		try {
			String sql = "SELECT * FROM USER "
					+ "WHERE User_name NOT IN ( SELECT friend FROM friend_list WHERE USER='" + userName + "') "
							+ "And User_name NOT IN( '" + userName + "')";
			
			pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(sql);
			
			while(rs.next()) {
				
				User user = new User();
				user.setEmail((String) rs.getObject(1));
				user.setFullName((String) rs.getObject(3));
				
				nonFriendUserList.add(user);
				
			}
		
		}
		catch (Exception e) {
			System.out.println("exception in fetching user list: " + e.getMessage());
			return null;
		} 
		finally {
			if(pst != null) {
				try {
					pst.close();
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}

		return nonFriendUserList;
		
	}
	
	public static boolean isUserNameAvailable(String userName) {

		// TODO asdfjals
		
		return false;
	} 

}
