package in.rapps.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.rapps.models.Address;
import in.rapps.models.Profile;
import in.rapps.models.User;


// Class to handle user action
public class UserService {

	static Connection con;

	static {
		con = ConnectionUtils.con;
	}
	// Method to fetch user data from database specific to given userName
	public static User getUserByEmail(String userName) {

		if(con == null) {
			return null;
		}
		// sql statement to retrieve user data from database except address 
		String sql1 = "SELECT * "
				+ "FROM user "
				+ "WHERE email_id='" + userName + "' Limit 1;";
		// sql statement to retrieve address detail of user from database
		String sql2 = "SELECT * from address WHERE user_email_id = '" + userName + "'Limit 1;";
		// initialize user object
		User user = new User();
		// initialize address object
		Address address = new Address();

		Statement pst1 = null, pst2 = null;

		try {
			pst1 = con.createStatement();
			ResultSet rs1 = pst1.executeQuery(sql1);
			pst2 = con.createStatement();
			ResultSet rs2 = pst2.executeQuery(sql2);

			// populate user object with user data except address
			while(rs1.next()){
				user.setEmail((String) rs1.getObject(1));
				user.setPassword((String) rs1.getObject(2));
				user.setFirstName((String) rs1.getObject(3));
				user.setLastName((String) rs1.getObject(4));
				user.setFullName((String) rs1.getObject(5));
				user.setPhone((String) rs1.getObject(6));
			}

			// populate address object with address details
			while(rs2.next()){
				address.setCity((String) rs2.getObject(1));
				address.setState((String) rs2.getObject(2));
				address.setCountry((String) rs2.getObject(3));
				address.setZipCode(Integer.parseInt((String) rs2.getObject(4)));
				address.setLandMark((String) rs2.getObject(5));
				address.setAddressLine1((String) rs2.getObject(6));
				address.setAddressLine2((String) rs2.getObject(7));
			}
			// populate user object with address details
			user.setAddress(address);
		}
		catch(Exception e) {
			System.out.println("Exception in Fetching User.");
		}
		finally {
			if(pst1 != null || pst2 != null) {
				try {
					pst1.close();
					pst2.close();
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}
		// return the user object to requester
		return user;

	}
	// Method to register a user into database
	public static boolean registerUser(String userName, String password, String fullName) {

		if(con == null) {
			return false;
		}

		PreparedStatement pst1 = null, pst2 = null;
		int x = 0, y = 0;

		try {
			// sql statement to insert user detail in user database except address detail
			String sql1 = "insert into user (email_id, Password,first_name, full_name) values('" + userName + "','" + password + "','" + fullName + "','" + fullName + "')";
			// sql statement to insert address detail of user in database
			String sql2 = "insert into address ( user_email_id) values ('" + userName + "');";
			pst1 = con.prepareStatement(sql1);
			x = pst1.executeUpdate();
			pst2 = con.prepareStatement(sql2);
			y = pst2.executeUpdate();

		}
		catch (Exception e) {
			System.out.println("exception in register user: " + e.getMessage());
			// if insertion of data in database fails, return false
			return false;
		} 
		finally {
			if(pst1 != null || pst2 != null) {
				try {
					pst1.close();
					pst2.close();
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}
		// if both user detail and address detail are inserted in database successufully, return true, otherwise false
		return (x > 0 && y > 0);

	}

	// Method to return the user list of users those are not friend with the requester
	public static List<User> getNonFriendUserList(String userName) {

		if(con == null) {
			return null;
		}
		// initialize the array list of user
		List<User> nonFriendUserList = new ArrayList<User>();

		PreparedStatement pst = null;

		try {
			// sql statement for fetching the list of user not friend with requester
			String sql = "SELECT * FROM USER "
					+ "WHERE email_id NOT IN ( SELECT friend_email FROM friend WHERE user_email='" + userName + "') "
					+ "And email_id NOT IN( '" + userName + "')";

			pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while(rs.next()) {

				User user = new User();
				user.setEmail((String) rs.getObject(1));
				user.setFullName((String) rs.getObject(3));
				// add the user to user list
				nonFriendUserList.add(user);

			}

		}
		catch (Exception e) {
			System.out.println("exception in fetching user list: " + e.getMessage());
			// if database read fails, return null
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
		// return the arraylist of user
		return nonFriendUserList;

	}
	// Method to return friend list of requester
	public static List<User> getFriendList(String userName) {

		if(con == null) {
			return null;
		}
		// initialize the friend lists 
		List<User> friendList = new ArrayList<User>();

		PreparedStatement pst = null;

		try {
			// sql statement for fetching all friends of requester
			String sql = "SELECT * FROM friend WHERE user_email='"+userName+"'";

			pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while(rs.next()) {

				User user = new User();
				user.setEmail((String) rs.getObject(2));
				user.setFullName((String) rs.getObject(3));
				// add friend to friend list
				friendList.add(user);

			}

		}
		catch (Exception e) {
			System.out.println("exception in fetching user list: " + e.getMessage());
			// if database read fails, return null
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
		// return friend list
		return friendList;

	}


	// Method checking the username availability for requester
	public static boolean isUserNameAvailable(String userName) {

		if(con == null) {
			return false;
		}
		// sql statement for selecting all the email_id from user database
		String sql = "SELECT email_id from user";
		// initializing availability flag
		boolean available = true;
		Statement pst = null;

		try {
			pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);

			while(rs.next()){
				if(userName.equals((String) rs.getObject(1)))
					// set availability to false if any email_id from database matches the requester's one
					available = false;
			}
		}	
		catch (Exception e) {
			System.out.println("exception in checking User Name availability: " + e.getMessage());
			// return false if fails to read database
			return false;
		} 
		finally {
			if(pst != null) {
				try {
					pst.close();
					// if no email_id match found, send flag without altering
					return available;
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}
		// if flag value is not returned from finally-try statement, return false
		return false;
	}



	// Method to update requester's profile
	public static User updateProfile(String userName, Profile profile) {
		if(con == null) {
			return null;
		}
		// set local variables with the details of profile in profile object
		String firstName = profile.getFirstName(), 
				lastName = profile.getLastName(),
				fullName = profile.getFullName(),
				phone = profile.getPhone(),
				addressLine1 = profile.getAddressLine1(),
				addressLine2 = profile.getAddressLine2(),
				landMark = profile.getLandMark(),
				city = profile.getCity(),
				state = profile.getState(),
				country = profile.getCountry();

		Integer zipCode = profile.getZipCode();
		// sql statement to update user detail fetched from profile object
		String sql1 = "update user set first_name = '" + firstName + "' , last_name = '" + lastName + "' , full_name = '" 
				+ fullName + "' , phone = '" + phone + "' where email_id = '" + userName + "';"; 

		// sql statement to update address detail of user fetched from profile object
		String sql2 = "update address set city = '" + city + "' , state = '" + state + "' , country = '" 
				+ country + "' , zipCode = '" + zipCode + "' , landMark = '" + landMark + "' , addressLine1 = '" 
				+ addressLine1 + "' , addressLine2 = '" + addressLine2 + "' where user_email_id = '" + userName + "';"; 

		// sql statement to fetch requester's updated details except address detail
		String sql3 = "SELECT * "
				+ "FROM user "
				+ "WHERE email_id='" + userName + "' Limit 1;";
		// sql statement to fetch requester's updated address details
		String sql4 = "SELECT * from address WHERE user_email_id = '" + userName + "'Limit 1;";
		// initialize user object
		User user = new User();
		// initialize address object
		Address address = new Address();
		PreparedStatement pst1 = null, pst2 = null;
		Statement pst3 = null, pst4 = null;
		int x = 0, y = 0;

		try {

			pst1 = con.prepareStatement(sql1);
			x = pst1.executeUpdate();
			pst2 = con.prepareStatement(sql2);
			y = pst2.executeUpdate();

			if(x > 0 && y > 0){
				pst3 = con.createStatement();
				ResultSet rs1 = pst3.executeQuery(sql3);
				pst4 = con.createStatement();
				ResultSet rs2 = pst4.executeQuery(sql4);
				while(rs1.next()){

					// populate user object with database result except address
					user.setEmail((String) rs1.getObject(1));
					user.setPassword((String) rs1.getObject(2));
					user.setFirstName((String) rs1.getObject(3));
					user.setLastName((String) rs1.getObject(4));
					user.setFullName((String) rs1.getObject(5));
					user.setPhone((String) rs1.getObject(6));
				}
				while(rs2.next()){
					// populate address object with database result 
					address.setCity((String) rs2.getObject(1));
					address.setState((String) rs2.getObject(2));
					address.setCountry((String) rs2.getObject(3));
					address.setZipCode(Integer.parseInt((String) rs2.getObject(4)));
					address.setLandMark((String) rs2.getObject(5));
					address.setAddressLine1((String) rs2.getObject(6));
					address.setAddressLine2((String) rs2.getObject(7));
				}

				// populate user object with address object
				user.setAddress(address);
			}
			else return null;
		}
		catch(Exception e) {
			System.out.println("Exception in Fetching User.");
		}
		finally {
			if(pst1 != null || pst2 != null) {
				try {
					pst1.close();
					pst2.close();
				} catch (Exception e) {
					System.out.println("Exception in closing statement");
				}
			}
		}
		// return updated user object after update of profile
		return user;
	} 

}
