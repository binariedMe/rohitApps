package in.rapps.servlets.user;

import in.rapps.models.User;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet to send the list of users that are not friend with the client
@WebServlet("/getUserList")
public class NonFriendUserListRetreiver extends HttpServlet {

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
		
		// Populating users' arraylist with users that are not friend with client
		List<User> users = UserService.getNonFriendUserList(userName);
		
		// build JSON of users arraylist
		String nonFriendUsersJson = JsonBuilder.getUsersJson(users);
		
		// send the users' list to client
		out.print(nonFriendUsersJson);
		
	}
	
}