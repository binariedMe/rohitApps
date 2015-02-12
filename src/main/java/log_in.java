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
@WebServlet("/log_in")
public class log_in extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException,NullPointerException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String Username = request.getParameter("user");
		String Password = request.getParameter("password");

		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", "root", "admin");
			try {
				Statement pst;
				String sql;
				ResultSet rs;

				sql = "SELECT * FROM user WHERE User_name='"+Username+"';";
				pst = con.createStatement();
				rs = pst.executeQuery(sql);


				String test = "[";
				String pass = null;
				int count = 0;
				while(rs.next()){
					String email = (String) rs.getObject(1);
					pass = (String) rs.getObject(2);
					String name = (String) rs.getObject(3);
					test += "{\"name\":\""+name+"\",\"email\":\""+email+"\"},";
					count++;
				}
				if(pass.equals(Password)){
					/*sql = "SELECT * FROM user_detail WHERE user='"+Username+"'";
					pst = con.createStatement();
					rs = pst.executeQuery(sql);

					while(rs.next()) {
						count++;
						String email = (String) rs.getObject(1);
						String name = (String) rs.getObject(2);
						String age = (String) rs.getObject(3);
						String sex = (String) rs.getObject(4);
						String address = (String) rs.getObject(5);
						test += "{\"name\":\""+name+"\",\"email\":\""+email+"\",\"age\":\""+age+"\",\"sex\":\""+sex+"\",\"address\":\""+address+"\"},";
					}*/

					if(count !=0 ) {
						test = test.substring(0, test.length() - 1);
					}

					out.println(test+"]");
				}
				else
					out.println(false);

				pst.close();

				if(con != null)
					con.close();

			} catch (SQLException e) {
				out.print(false);
				con.close();
			}


		} catch (Exception ex) {
			out.print(false);
		}
	}
}