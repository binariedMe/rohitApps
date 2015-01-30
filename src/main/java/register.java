import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class register extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException,NullPointerException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();

		String Username = request.getParameter("user");
		String Password = request.getParameter("password");
		String Name = request.getParameter("name");

		Connection con;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", "root", "admin");
			try {
				PreparedStatement pst;
				String sql = "insert into user (User_name, Password, Name) values('"+Username+ "','"  +Password +"','"+Name +"')";
				pst = con.prepareStatement(sql);

				int x = pst.executeUpdate();
				sql = "insert into user (user,name) values ('"+Username+"','"+Name+"')";
				pst = con.prepareStatement(sql);
				x = pst.executeUpdate();
				pst.close();

				if (x > 0) {
					out.print(true);
				}
				if(con != null)
					con.close();
			} catch (SQLException e) {
				out.print(false);
				con.close();
			}


		} catch (Exception e) {
			out.print(false);
		}
	}
}