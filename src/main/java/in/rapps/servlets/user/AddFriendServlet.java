package in.rapps.servlets.user;

import in.rapps.utils.FriendService;

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

@WebServlet("/addFriend")
public class AddFriendServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String userName = request.getParameter("user");
		String friendEmail = request.getParameter("friend");
		String friendName = request.getParameter("friend_name");

		boolean isFriendAdded = FriendService.addFriend(userName, friendEmail, friendName);

		if(out != null) {
			out.print(isFriendAdded);
		}
		
	}
}