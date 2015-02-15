package in.rapps.servlets.authentication;

import in.rapps.models.User;
import in.rapps.utils.JsonBuilder;
import in.rapps.utils.UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet for handling Login request

@WebServlet("/log_in")
public class LoginServlet extends HttpServlet {

	
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
		String password = request.getParameter("password");

		try {
			
			// Fetching user data corresponding to the userName received from request
			User user = UserService.getUserByEmail(userName);
			
			// validating if password from request matched the password saved in database
			if(password != null && password.equals(user.getPassword())) {
				
				// If log in credentials are correct, build JSON of User Details and send in response
				String userData = JsonBuilder.getUserJson(user);
				out.print(userData);
			}
			else {
				// If Login credentials are wrong, send false in response
				out.print(false);
			}
		}
		catch (Exception e) {
			// If servlet fails to read the database, send false in response
			out.print(false);
		}

	}
}