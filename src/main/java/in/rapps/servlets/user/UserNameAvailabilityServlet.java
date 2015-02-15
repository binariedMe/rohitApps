package in.rapps.servlets.user;


import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet to check if the user name entered by client is unique
@WebServlet("/isUserNameAvailable")
public class UserNameAvailabilityServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		// Populating local variables with request data
		String userName = request.getParameter("userName");
		
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// set flag of username availability after checking it against user database
		boolean isAvailable = UserService.isUserNameAvailable(userName);
		
		if(out != null) {
			// send the availability status to client
			out.print(isAvailable);
		}
		
	}
}