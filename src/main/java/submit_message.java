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

@WebServlet("/submitMessage")
public class submit_message extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException,NullPointerException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		
		String user = request.getParameter("user");
		String recepient = request.getParameter("recepient");
		String message = request.getParameter("message");
		
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat?autoReconnect=true", "root", "admin");
			try {
				PreparedStatement pst;
				
				String sql = "insert into message_mapping (sender, recepient, message) values('"+user+ "','"  +recepient+"','"+message+"')";
				pst = con.prepareStatement(sql);
				int x = pst.executeUpdate();
				pst.close();

				if (x > 0) {
					out.print(true);
					
					
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