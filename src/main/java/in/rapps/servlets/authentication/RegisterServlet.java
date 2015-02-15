package in.rapps.servlets.authentication;
import in.rapps.utils.UserService;

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


// Servled to register client 
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

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
		String fullName = request.getParameter("name");
// set flag after registering a client
		boolean registrationSuccess = UserService.registerUser(userName, password, fullName);
		// send the registration status to client
		out.print(registrationSuccess);
	}
}