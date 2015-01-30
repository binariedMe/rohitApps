import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getUserList")
public class getUserList extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException,NullPointerException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String user = request.getParameter("user");
		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", "root", "admin");
			try {
				Statement pst;
				/*String sql = "insert into chatbox (user1, user2, message) values('"+senderUserName+ "','"  +listenerUserName +"','"+messageText +"')";*/
				String sql = "SELECT * FROM USER WHERE User_name NOT IN ( SELECT friend FROM friend_list WHERE USER='"+user+"') And User_name NOT IN( '"+user+"')";
				pst = con.createStatement();
				//pst.setString(1, lastcode);
				ResultSet rs = pst.executeQuery(sql);
				//pst.close();
				
				String test = "[";
				
				int count = 0;
				
				while(rs.next()) {
					count++;
					String userEmail = (String) rs.getObject(1);
					String userName = (String) rs.getObject(3);
					//out.println("{\"userName\""+":"+"\"Rohit Kumar\"}");
					test += "{\"userName\""+":"+"\""+userName+"\",\"userEmail\""+":"+"\""+userEmail+"\"},";
				}
				
				if(count !=0 ) {
					test = test.substring(0, test.length() - 1);
				}

			out.println(test + "]");	
				pst.close();
				if(con != null)
					con.close();

			} catch (SQLException e) {
				out.print("message");
				con.close();
			}


		} catch (Exception ex) {
			out.print("messagessss");
		}
	}
}