package in.rapps.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.rapps.models.Message;
import in.rapps.models.User;

// Class to handle chat service of requester
public class MessageService {

	static Connection con;

	static {
		con = ConnectionUtils.con;
	}
	// Method to retrieve message from database
	public static List<Message> getMessageList(String userName, String friendEmail) {

		if(con == null) {
			return null;
		}
		// intialise message list
		List<Message> messageList = new ArrayList<Message>();

		PreparedStatement pst = null;

		try {
			// sql statement for fetching message from message database
			String sql = "SELECT * FROM message WHERE (sender_email='" + userName + "' and recepient_email='" + friendEmail + "') "
					+ "OR (sender_email='" + friendEmail + "' and recepient_email='" + userName + "');";

			pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while(rs.next()) {
				// initialize a temporary message object and populate one instance of message
				Message message = new Message();
				message.setSenderEmail((String) rs.getObject(2));
				message.setReceipientEmail((String) rs.getObject(3));
				message.setMessage((String) rs.getObject(4));
				// add message instance to message list
				messageList.add(message);
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
		// return message list
		return messageList;

	}

	// Method to submit message to database
	public static boolean submitMessage(String userName, String recepientEmail, String message) {

		if(con == null) {
			return false;
		}

		PreparedStatement pst = null;
		int x = 0;

		try {
			// sql statement for inserting message in message database
			String sql = "insert into message (sender_email, recepient_email, message_text) "
					+ "values('" + userName + "','" + recepientEmail + "','" + message + "')";
			pst = con.prepareStatement(sql);
			x = pst.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("exception in register user: " + e.getMessage());
			// return false if message submission fails
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
		// return true if successful submission of message, false otherwise
		return (x > 0);

	}


}
