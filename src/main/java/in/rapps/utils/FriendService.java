package in.rapps.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.rapps.models.User;

public class FriendService {

	static Connection con;

	static {
		con = ConnectionUtils.con;
	}

	public static boolean addFriend(String userName, String friendEmail, String friendName) {

		boolean isAdded = false;

		if(con == null) {
			return isAdded;
		}

		String sql = "insert into friend_list ( user, friend, friend_name) "
				+ "values('" + userName + "','" + friendEmail + "','" + friendName + "')";

		Statement pst = null;

		try {
			pst = con.createStatement();
			int x = pst.executeUpdate(sql);
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

		return isAdded;

	}

	public static boolean removeFriend(String userName, String friendEmail) {

		boolean isRemoved = false;

		if(con == null) {
			return isRemoved;
		}

		String sql = "delete from friend_list where user='" + userName + "' and friend='" + friendEmail + "'";
		
		Statement pst = null;

		try {
			pst = con.createStatement();
			int x = pst.executeUpdate(sql);
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

		return isRemoved;

	}

}
