package in.rapps.servlets.user;
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

@WebServlet("/getFriendList")
public class GetFriendListServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, NullPointerException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String user = request.getParameter("user");

		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", "root", "");
			try {
				Statement pst;
				String sql = "SELECT * FROM friend_list WHERE USER='"+user+"'";
				pst = con.createStatement();
				ResultSet rs = pst.executeQuery(sql);
				String test = "[";
				
				int count = 0;
				
				while(rs.next()) {
					count++;
					String userEmail = (String) rs.getObject(2);
					String userName = (String) rs.getObject(3);
					test += "{\"userName\":\""+userName+"\",\"userEmail\":\""+userEmail+"\"},";
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