package in.rapps.servlets.user;
import in.rapps.utils.FriendService;

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

// Servlet to remove a requested user from the friend list of client
@WebServlet("/removeFriend")
public class FriendRemoverServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Populating local variables with request data
		String userName = request.getParameter("user");
		String friendEmail = request.getParameter("friend");
		
		// Set the flag to true after removing friend from database if successful, false otherwise
		boolean isRemoved = FriendService.removeFriend(userName, friendEmail);

		if(out != null) {
			// send the friend removal status to client
			out.print(isRemoved);
		}
		
	}
}