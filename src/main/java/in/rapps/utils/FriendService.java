package in.rapps.utils;

import java.sql.Connection;
import java.sql.Statement;

// Class to handle request related to friend of requester
public class FriendService {

	static Connection con;

	static {
		con = ConnectionUtils.con;
	}
	// Method to add a friend in friend list of requester
	public static boolean addFriend(String userName, String friendEmail, String friendName) {

		// initialize status of addition of friend 
		boolean isAdded = false;

		if(con == null) {
			return isAdded;
		}
		// sql statement to add friend to friend list
		String sql = "insert into friend ( user_email, friend_email, friend_name) "
				+ "values('" + userName + "','" + friendEmail + "','" + friendName + "')";

		Statement pst = null;

		try {
			pst = con.createStatement();
			int x = pst.executeUpdate(sql);
			// set addition flag to true if successful addition, false otherwise
			isAdded = (x > 0);
		}
		catch(Exception e) {
			System.out.println("Exception in Fetching User.");
			return isAdded;
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
		// return addition of friend flag 
		return isAdded;

	}


	// Method to remove friend
	public static boolean removeFriend(String userName, String friendEmail) {


		// initialize friend removal flag
		boolean isRemoved = false;

		if(con == null) {
			return isRemoved;
		}

		// sql statement for removing friend from friend list of requester
		String sql = "delete from friend where user_email='" + userName + "' and friend_email='" + friendEmail + "'";

		Statement pst = null;

		try {
			pst = con.createStatement();
			int x = pst.executeUpdate(sql);
			// set friend removal flag to true if successful removal, false otherwise
			isRemoved = (x > 0);
		}
		catch(Exception e) {
			System.out.println("Exception in Fetching User.");
			return isRemoved;
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
		// return friend removal status
		return isRemoved;

	}

}
