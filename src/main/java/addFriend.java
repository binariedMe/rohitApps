import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addFriend")
public class addFriend extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException,NullPointerException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		
		String user = request.getParameter("user");
		String friend = request.getParameter("friend");
		String friend_name = request.getParameter("friend_name");
		
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", "root", "admin");
			try {
				PreparedStatement pst;
				
				String sql = "insert into friend_list (user,friend,friend_name) values('"+user	+ "','"  +friend+ "','"  +friend_name+"')";
				pst = con.prepareStatement(sql);
				//pst.setString(1, lastcode);
				int x = pst.executeUpdate();
				pst.close();

				if (x > 0) {
					out.print(true);
					// rd = request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp");
					// rd.forward(request, response);
				}
				if(con != null)
					con.close();

			} catch (SQLException e) {
				System.out.println("cant load driver" + e);
				out.print(e);
				con.close();
			}


		} catch (Exception e) {
			System.out.println("cant establish connection" + e);
			out.print("messagessss");
		}
	}
}