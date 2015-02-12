package in.rapps.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.rapps.models.Message;
import in.rapps.models.User;

public class MessageService {

	static Connection con;

	static {
		con = ConnectionUtils.con;
	}

	public static List<Message> getMessageList(String userName, String friendEmail) {

		if(con == null) {
			return null;
		}

		List<Message> messageList = new ArrayList<Message>();

		PreparedStatement pst = null;

		try {
			String sql = "SELECT * FROM message_mapping WHERE (sender='" + userName + "' and recepient='" + friendEmail + "') "
					+ "OR (sender='" + friendEmail + "' and recepient='" + userName + "');";

			pst = con.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while(rs.next()) {
				Message message = new Message();
				message.setSenderEmail((String) rs.getObject(1));
				message.setReceipientEmail((String) rs.getObject(3));
				message.setMessage((String) rs.getObject(3));

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

		return messageList;

	}

	public static boolean submitMessage(String userName, String recepientEmail, String message) {

		if(con == null) {
			return false;
		}

		PreparedStatement pst = null;
		int x = 0;

		try {
			String sql = "insert into message_mapping (sender, recepient, message) "
					+ "values('" + userName + "','" + recepientEmail + "','" + message + "')";
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


}
