package in.rapps.servlets.user;
import in.rapps.models.User;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet to send the list of friend of client
@WebServlet("/getFriendList")
public class GetFriendListServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, NullPointerException {

		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Populating local variables with request data
		String userName = request.getParameter("user");
		
		// Populating friend arraylist with list of friend of client
		List<User> friendList = UserService.getFriendList(userName);

		
		// Build JSON of friend arraylist
		String friendListJson = JsonBuilder.getUsersJson(friendList);
		
		// Send friend list to client
		out.print(friendListJson);

	}
}